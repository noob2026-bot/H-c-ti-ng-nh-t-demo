package com.example.data

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "lesson_progress")
data class LessonProgress(
    @PrimaryKey val lessonNum: Int,
    val highScore: Int,
    val avgReactionTime: Double,
    val completedAt: Long = System.currentTimeMillis()
)

@Dao
interface LessonProgressDao {
    @Query("SELECT * FROM lesson_progress ORDER BY lessonNum ASC")
    fun getAllProgress(): Flow<List<LessonProgress>>

    @Query("SELECT * FROM lesson_progress WHERE lessonNum = :lessonNum")
    suspend fun getProgressForLesson(lessonNum: Int): LessonProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgress(progress: LessonProgress)

    @Query("DELETE FROM lesson_progress")
    suspend fun clearAllProgress()
}

@Database(entities = [LessonProgress::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lessonProgressDao(): LessonProgressDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "nihongo_reaction_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

class LessonProgressRepository(private val dao: LessonProgressDao) {
    val allProgress: Flow<List<LessonProgress>> = dao.getAllProgress()

    suspend fun getProgressForLesson(lessonNum: Int) = dao.getProgressForLesson(lessonNum)

    suspend fun saveProgress(lessonNum: Int, score: Int, avgReactionTime: Double) {
        val existing = dao.getProgressForLesson(lessonNum)
        val newHighScore = if (existing != null) maxOf(existing.highScore, score) else score
        val bestReactionTime = if (existing != null && score >= existing.highScore) {
            avgReactionTime
        } else if (existing != null) {
            existing.avgReactionTime
        } else {
            avgReactionTime
        }
        dao.saveProgress(
            LessonProgress(
                lessonNum = lessonNum,
                highScore = newHighScore,
                avgReactionTime = bestReactionTime,
                completedAt = System.currentTimeMillis()
            )
        )
    }

    suspend fun clearAll() = dao.clearAllProgress()
}
