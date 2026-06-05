package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.animation.core.tween
import com.example.ui.FeedbackState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.NihongoViewModel
import com.example.ui.GameState
import com.example.ui.LetterStyle
import com.example.ui.QuizDirection
import com.example.data.WordItem
import com.example.data.LessonProgress
import com.example.data.VocabularyDb
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFF09090B) // Cozy dark zinc theme
                ) { innerPadding ->
                    NihongoReactionApp(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NihongoReactionApp(
    modifier: Modifier = Modifier,
    viewModel: NihongoViewModel = viewModel()
) {
    val gameState by viewModel.gameState.collectAsState()
    val selectedLesson by viewModel.selectedLesson.collectAsState()
    val letterStyle by viewModel.letterStyle.collectAsState()
    val quizDirection by viewModel.quizDirection.collectAsState()
    val speechSpeed by viewModel.speechSpeed.collectAsState()
    val currentQuestion by viewModel.currentQuestion.collectAsState()
    val options by viewModel.options.collectAsState()
    val score by viewModel.score.collectAsState()
    val combo by viewModel.combo.collectAsState()
    val maxCombo by viewModel.maxCombo.collectAsState()
    val timeLeft by viewModel.timeLeft.collectAsState()
    val feedback by viewModel.feedback.collectAsState()
    val wrongAnswers by viewModel.wrongAnswers.collectAsState()
    val reactionTimes by viewModel.reactionTimes.collectAsState()
    val completedProgress by viewModel.completedProgressList.collectAsState()

    Crossfade(
        targetState = gameState,
        animationSpec = tween(300),
        label = "gameState_transition"
    ) { state ->
        when (state) {
            GameState.DASHBOARD -> {
                DashboardScreen(
                    completedProgress = completedProgress,
                    letterStyle = letterStyle,
                    quizDirection = quizDirection,
                    speechSpeed = speechSpeed,
                    onSelectLesson = { num -> viewModel.startLesson(num) },
                    onStyleChange = { style -> viewModel.setLetterStyle(style) },
                    onDirectionChange = { dir -> viewModel.setQuizDirection(dir) },
                    onSpeedChange = { speed -> viewModel.setSpeechSpeed(speed) },
                    onClearHistory = { viewModel.clearHistory() },
                    modifier = modifier
                )
            }
            GameState.PLAYING -> {
                PlayingScreen(
                    selectedLesson = selectedLesson,
                    letterStyle = letterStyle,
                    quizDirection = quizDirection,
                    currentQuestion = currentQuestion,
                    options = options,
                    score = score,
                    combo = combo,
                    timeLeft = timeLeft,
                    feedback = feedback,
                    onSelectOption = { opt -> viewModel.selectOption(opt) },
                    onSpeak = { viewModel.speakCurrentWord() },
                    onBack = { viewModel.returnToDashboard() },
                    modifier = modifier
                )
            }
            GameState.RESULTS -> {
                val avgTime = if (reactionTimes.isNotEmpty()) {
                    reactionTimes.average() / 1000.0
                } else {
                    0.0
                }
                ResultsScreen(
                    selectedLesson = selectedLesson,
                    totalScore = score,
                    maxCombo = maxCombo,
                    avgReactionTime = avgTime,
                    wrongAnswers = wrongAnswers,
                    onRetry = { viewModel.startLesson(selectedLesson) },
                    onBackToDashboard = { viewModel.returnToDashboard() },
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun DashboardScreen(
    completedProgress: List<LessonProgress>,
    letterStyle: LetterStyle,
    quizDirection: QuizDirection,
    speechSpeed: Float,
    onSelectLesson: (Int) -> Unit,
    onStyleChange: (LetterStyle) -> Unit,
    onDirectionChange: (QuizDirection) -> Unit,
    onSpeedChange: (Float) -> Unit,
    onClearHistory: () -> Unit,
    modifier: Modifier = Modifier
) {
    val progressMap = remember(completedProgress) {
        completedProgress.associateBy { it.lessonNum }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF09090B))
            .padding(horizontal = 20.dp)
    ) {
        // iOS Header Title Block
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "NHẬT PHẢN XẠ",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp,
                        color = Color.White
                    )
                )
                Text(
                    text = "50 Bài Minna • Không dùng Romaji",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFF71717A),
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFFEF3C7))
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Tiến độ",
                        tint = Color(0xFFD97706),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Tiến độ: ${completedProgress.size}/50",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFB45309)
                        )
                    )
                }
            }
        }

        // Segment Switch and Sound Controllers Container
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF18181B)),
            border = BorderStroke(1.dp, Color(0xFF27272A)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                
                // Selector Switch 
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Dạng hiển thị",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color(0xFFA1A1AA),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF09090B))
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        listOf(
                            LetterStyle.KANA to "ひらがな (Kana)",
                            LetterStyle.KANJI to "漢字 (Kanji)"
                        ).forEach { (style, label) ->
                            val selected = letterStyle == style
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (selected) Color(0xFF27272A) else Color.Transparent)
                                    .clickable { onStyleChange(style) }
                                    .padding(vertical = 8.dp)
                            ) {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (selected) Color.White else Color(0xFF71717A)
                                    )
                                )
                            }
                        }
                    }
                }

                // Quiz Direction Switch 
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Hướng phản xạ",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color(0xFFA1A1AA),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF09090B))
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        listOf(
                            QuizDirection.JA_TO_VI to "Nhật ➔ Việt",
                            QuizDirection.VI_TO_JA to "Việt ➔ Nhật"
                        ).forEach { (dir, label) ->
                            val selected = quizDirection == dir
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (selected) Color(0xFF27272A) else Color.Transparent)
                                    .clickable { onDirectionChange(dir) }
                                    .padding(vertical = 8.dp)
                            ) {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (selected) Color.White else Color(0xFF71717A)
                                    )
                                )
                            }
                        }
                    }
                }

                // Audio Speed Adjuster
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Audio Volume",
                                tint = Color(0xFFF59E0B),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Tốc độ phát âm gốc",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color(0xFFA1A1AA),
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Text(
                            text = "${"%.2f".format(speechSpeed)}x",
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = Color(0xFFF59E0B),
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Slider(
                        value = speechSpeed,
                        onValueChange = onSpeedChange,
                        valueRange = 0.5f..1.2f,
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFFF59E0B),
                            activeTrackColor = Color(0xFFF59E0B),
                            inactiveTrackColor = Color(0xFF27272A)
                        ),
                        modifier = Modifier.height(28.dp)
                    )
                }

                // Clear Progress link
                if (completedProgress.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = "Xoá lịch sử học",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color(0xFFEF4444),
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .clickable { onClearHistory() }
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }

        Text(
            text = "LỰA CHỌN BÀI HỌC",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Black,
                letterSpacing = 1.5.sp,
                color = Color(0xFF52525B)
            ),
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
        )

        // Robust Grid of 50 Lessons
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 16.dp)
        ) {
            items((1..50).toList()) { num ->
                val progress = progressMap[num]
                val hasProgress = progress != null

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFF18181B),
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (hasProgress) Color(0xFF10B981).copy(alpha = 0.4f) else Color(0xFF27272A)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectLesson(num) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Numeric Icon box
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    if (hasProgress) Color(0xFF10B981).copy(alpha = 0.15f)
                                    else Color(0xFF27272A)
                                )
                        ) {
                            Text(
                                text = num.toString(),
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = if (hasProgress) Color(0xFF34D399) else Color(0xFFD4D4D8)
                                )
                            )
                        }

                        // Text metadata
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Bài học $num",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = Color.White
                                )
                            )
                            if (hasProgress) {
                                Text(
                                    text = "Best: ${progress?.highScore}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color(0xFF10B981),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    text = "Reflex: ${"%.2f".format(progress?.avgReactionTime ?: 0.0)}s",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color(0xFFA1A1AA),
                                        fontSize = 9.sp
                                    )
                                )
                            } else {
                                Text(
                                    text = "Chưa thử",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color(0xFF52525B),
                                        fontSize = 10.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlayingScreen(
    selectedLesson: Int,
    letterStyle: LetterStyle,
    quizDirection: QuizDirection,
    currentQuestion: WordItem?,
    options: List<WordItem>,
    score: Int,
    combo: Int,
    timeLeft: Int,
    feedback: FeedbackState?,
    onSelectOption: (WordItem) -> Unit,
    onSpeak: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF09090B))
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        
        // Header Controls
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF18181B))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Trở về",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (combo > 1) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFF59E0B).copy(alpha = 0.15f))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "$combo Combo 🔥",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color(0xFFF59E0B),
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF27272A))
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Bài $selectedLesson",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

            // Timed progress bar
            val colorFactor = if (timeLeft < 10) Color(0xFFEF4444) else Color.White
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Thời gian phản xạ",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color(0xFF52525B),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "$timeLeft giây",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = colorFactor,
                            fontWeight = FontWeight.Black
                        )
                    )
                }
                LinearProgressIndicator(
                    progress = { timeLeft / 45f },
                    color = colorFactor,
                    trackColor = Color(0xFF18181B),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(CapsuleShape)
                )
            }
        }

        // Active Question Card
        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF18181B)),
            border = BorderStroke(1.dp, Color(0xFF27272A)),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                // Speaker Floating play trigger
                IconButton(
                    onClick = onSpeak,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF27272A))
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Phát âm thanh",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = if (currentQuestion != null) {
                            if (quizDirection == QuizDirection.JA_TO_VI) {
                                if (letterStyle == LetterStyle.KANA) currentQuestion.word else currentQuestion.kanji
                            } else {
                                currentQuestion.meaning
                            }
                        } else "",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontSize = if (quizDirection == QuizDirection.VI_TO_JA) 32.sp else 44.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            lineHeight = if (quizDirection == QuizDirection.VI_TO_JA) 38.sp else 52.sp
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }

        // Answer Selectors Block
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(
                text = if (quizDirection == QuizDirection.JA_TO_VI) "CHỌN NGHĨA TIẾNG VIỆT CHÍNH XÁC" else "CHỌN TIẾNG NHẬT / KANJI CHÍNH XÁC",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.2.sp,
                    color = Color(0xFF52525B),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
            )

            options.forEach { opt ->
                val isSelected = feedback?.selectedId == opt.id
                val isCorrectAnswer = opt.id == currentQuestion?.id

                val containerColor = when {
                    feedback == null -> Color(0xFF18181B)
                    isCorrectAnswer -> Color(0xFF10B981).copy(alpha = 0.15f) // Correct Highlight
                    isSelected -> Color(0xFFEF4444).copy(alpha = 0.15f) // Wrong Selection Accent
                    else -> Color(0xFF18181B).copy(alpha = 0.4f)
                }

                val borderColor = when {
                    feedback == null -> Color(0xFF27272A)
                    isCorrectAnswer -> Color(0xFF34D399)
                    isSelected -> Color(0xFFF87171)
                    else -> Color(0xFF27272A).copy(alpha = 0.3f)
                }

                val textColor = when {
                    feedback == null -> Color.White
                    isCorrectAnswer -> Color(0xFF34D399)
                    isSelected -> Color(0xFFF87171)
                    else -> Color(0xFF52525B)
                }

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = containerColor,
                    border = BorderStroke(1.5.dp, borderColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = feedback == null) { onSelectOption(opt) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp, vertical = 14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val optionText = if (quizDirection == QuizDirection.JA_TO_VI) {
                            opt.meaning
                        } else {
                            if (letterStyle == LetterStyle.KANA) opt.word else opt.kanji
                        }
                        Text(
                            text = optionText,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = textColor
                            ),
                            modifier = Modifier.weight(1f)
                        )

                        if (feedback != null) {
                            if (isCorrectAnswer) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Đúng",
                                    tint = Color(0xFF10B981),
                                    modifier = Modifier.size(20.dp)
                                )
                            } else if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Sai",
                                    tint = Color(0xFFEF4444),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ResultsScreen(
    selectedLesson: Int,
    totalScore: Int,
    maxCombo: Int,
    avgReactionTime: Double,
    wrongAnswers: List<WordItem>,
    onRetry: () -> Unit,
    onBackToDashboard: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF09090B))
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        
        Spacer(modifier = Modifier.height(16.dp))

        // Large Trophy Header Block
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(76.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFEF3C7))
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Success",
                    tint = Color(0xFFD97706),
                    modifier = Modifier.size(38.dp)
                )
            }

            Text(
                text = "HOÀN THÀNH BÀI $selectedLesson",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.5.sp,
                    color = Color(0xFF71717A)
                ),
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = "$totalScore",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 54.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            )
        }

        // Metrics Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF18181B)),
                border = BorderStroke(1.dp, Color(0xFF27272A)),
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Phản xạ trung bình",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color(0xFF71717A),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "${"%.2f".format(avgReactionTime)}s",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                    )
                }
            }

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF18181B)),
                border = BorderStroke(1.dp, Color(0xFF27272A)),
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Combo tối đa",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color(0xFF71717A),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "${maxCombo}x",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                    )
                }
            }
        }

        // Mistook Words Quick revision layout
        if (wrongAnswers.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFF18181B))
                    .border(1.dp, Color(0xFF27272A), RoundedCornerShape(24.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Từ vựng phản xạ lỗi (${wrongAnswers.size})",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFEF4444).copy(alpha = 0.15f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Ôn tập ngay",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color(0xFFF87171),
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            )
                        )
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    wrongAnswers.forEach { word ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFF09090B))
                                .padding(horizontal = 14.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = word.word,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                )
                                if (word.kanji != word.word) {
                                    Text(
                                        text = word.kanji,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Color(0xFF71717A)
                                        )
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xFF27272A))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = word.meaning,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color(0xFFD4D4D8),
                                        fontWeight = FontWeight.Medium
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        } else {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF10B981).copy(alpha = 0.08f)),
                border = BorderStroke(1.dp, Color(0xFF10B981).copy(alpha = 0.25f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Thành tích hoàn hảo! Phản xạ chính xác 100% ✨",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color(0xFF34D399),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }

        // Action Buttons Row
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF09090B)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Chơi lại",
                        tint = Color(0xFF09090B),
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Luyện tập lại bài $selectedLesson",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Black
                        )
                    )
                }
            }

            Button(
                onClick = onBackToDashboard,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF18181B),
                    contentColor = Color(0xFFA1A1AA)
                ),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color(0xFF27272A)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text(
                    text = "Trở về danh sách bài",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

// Simple shape token fallback
private val CapsuleShape = RoundedCornerShape(50)
