package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import com.example.util.AudioHelper
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class GameState {
    DASHBOARD, PLAYING, RESULTS
}

enum class LetterStyle {
    KANA, KANJI
}

enum class QuizDirection {
    JA_TO_VI, VI_TO_JA
}

data class FeedbackState(
    val isCorrect: Boolean,
    val selectedId: String
)

class NihongoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LessonProgressRepository
    private val audioHelper: AudioHelper = AudioHelper(application)

    // --- GAME STATE FLOWS ---
    private val _gameState = MutableStateFlow(GameState.DASHBOARD)
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private val _selectedLesson = MutableStateFlow(1)
    val selectedLesson: StateFlow<Int> = _selectedLesson.asStateFlow()

    private val _letterStyle = MutableStateFlow(LetterStyle.KANA)
    val letterStyle: StateFlow<LetterStyle> = _letterStyle.asStateFlow()

    private val _quizDirection = MutableStateFlow(QuizDirection.JA_TO_VI)
    val quizDirection: StateFlow<QuizDirection> = _quizDirection.asStateFlow()

    private val _speechSpeed = MutableStateFlow(0.85f)
    val speechSpeed: StateFlow<Float> = _speechSpeed.asStateFlow()

    private val _currentQuestion = MutableStateFlow<WordItem?>(null)
    val currentQuestion: StateFlow<WordItem?> = _currentQuestion.asStateFlow()

    private val _options = MutableStateFlow<List<WordItem>>(emptyList())
    val options: StateFlow<List<WordItem>> = _options.asStateFlow()

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    private val _combo = MutableStateFlow(0)
    val combo: StateFlow<Int> = _combo.asStateFlow()

    private val _maxCombo = MutableStateFlow(0)
    val maxCombo: StateFlow<Int> = _maxCombo.asStateFlow()

    private val _timeLeft = MutableStateFlow(45)
    val timeLeft: StateFlow<Int> = _timeLeft.asStateFlow()

    private val _feedback = MutableStateFlow<FeedbackState?>(null)
    val feedback: StateFlow<FeedbackState?> = _feedback.asStateFlow()

    private val _wrongAnswers = MutableStateFlow<List<WordItem>>(emptyList())
    val wrongAnswers: StateFlow<List<WordItem>> = _wrongAnswers.asStateFlow()

    private val _reactionTimes = MutableStateFlow<List<Long>>(emptyList())
    val reactionTimes: StateFlow<List<Long>> = _reactionTimes.asStateFlow()

    private var questionStartTime: Long = 0
    private var timerJob: Job? = null

    // Observe progress list reactively from local Room DB
    val completedProgressList: StateFlow<List<LessonProgress>>

    init {
        val database = AppDatabase.getDatabase(application)
        repository = LessonProgressRepository(database.lessonProgressDao())
        completedProgressList = repository.allProgress
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }

    fun setLetterStyle(style: LetterStyle) {
        _letterStyle.value = style
    }

    fun setQuizDirection(direction: QuizDirection) {
        _quizDirection.value = direction
    }

    fun setSpeechSpeed(speed: Float) {
        _speechSpeed.value = speed
    }

    fun speakCurrentWord() {
        _currentQuestion.value?.let {
            audioHelper.speak(it.word, _speechSpeed.value)
        }
    }

    fun startLesson(lessonNum: Int) {
        _selectedLesson.value = lessonNum
        _score.value = 0
        _combo.value = 0
        _maxCombo.value = 0
        _timeLeft.value = 45
        _wrongAnswers.value = emptyList()
        _reactionTimes.value = emptyList()
        _feedback.value = null
        _gameState.value = GameState.PLAYING

        generateNextQuestion(lessonNum)
        startTimer()
    }

    private fun generateNextQuestion(lessonNum: Int) {
        _feedback.value = null
        val lessonWords = VocabularyDb.database[lessonNum] ?: return
        if (lessonWords.isEmpty()) return

        // Pick target word
        val targetWord = lessonWords.random()
        
        // Pick smart distractors
        val distractors = VocabularyDb.allVocab
            .filter { it.word != targetWord.word && it.meaning != targetWord.meaning }
            .shuffled()
            .take(3)

        val mergedOptions = (distractors + targetWord).shuffled()

        _currentQuestion.value = targetWord
        _options.value = mergedOptions
        questionStartTime = System.currentTimeMillis()

        // Speak word
        audioHelper.speak(targetWord.word, _speechSpeed.value)
    }

    fun selectOption(selectedItem: WordItem) {
        if (_feedback.value != null || _gameState.value != GameState.PLAYING) return

        val reactionTime = System.currentTimeMillis() - questionStartTime
        _reactionTimes.value = _reactionTimes.value + reactionTime

        val target = _currentQuestion.value ?: return
        val isCorrect = selectedItem.id == target.id

        audioHelper.playSfx(isCorrect)
        _feedback.value = FeedbackState(isCorrect, selectedItem.id)

        if (isCorrect) {
            val speedBonus = if (reactionTime < 1500) 50 else if (reactionTime < 3000) 20 else 5
            val pointsEarned = (100 + speedBonus) * (1 + (_combo.value / 2))
            _score.value = _score.value + pointsEarned
            
            val nextCombo = _combo.value + 1
            _combo.value = nextCombo
            if (nextCombo > _maxCombo.value) {
                _maxCombo.value = nextCombo
            }
            // Add time reward
            _timeLeft.value = minOf(_timeLeft.value + 2, 45)
        } else {
            if (!_wrongAnswers.value.any { it.id == target.id }) {
                _wrongAnswers.value = _wrongAnswers.value + target
            }
            _combo.value = 0
            // Subtract time penalty
            _timeLeft.value = maxOf(_timeLeft.value - 4, 0)
        }

        viewModelScope.launch {
            delay(750)
            if (_gameState.value == GameState.PLAYING) {
                generateNextQuestion(_selectedLesson.value)
            }
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_timeLeft.value > 0) {
                delay(1000)
                _timeLeft.value = _timeLeft.value - 1
            }
            // Game Finished
            endGame()
        }
    }

    private suspend fun endGame() {
        timerJob?.cancel()
        val finalScore = _score.value
        val avgTime = if (_reactionTimes.value.isNotEmpty()) {
            _reactionTimes.value.average() / 1000.0
        } else {
            0.0
        }

        // Save progress to Room DB
        repository.saveProgress(_selectedLesson.value, finalScore, avgTime)
        _gameState.value = GameState.RESULTS
    }

    fun returnToDashboard() {
        timerJob?.cancel()
        _gameState.value = GameState.DASHBOARD
    }

    fun clearHistory() {
        viewModelScope.launch {
            repository.clearAll()
        }
    }

    override fun onCleared() {
        super.onCleared()
        audioHelper.shutdown()
    }
}
