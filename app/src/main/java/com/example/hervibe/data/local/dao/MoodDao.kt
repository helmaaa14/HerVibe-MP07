package com.example.hervibe.data.local.dao

import androidx.room.*
import com.example.hervibe.data.local.entity.MoodLog
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMood(mood: MoodLog): Long

    @Update
    suspend fun updateMood(mood: MoodLog)

    @Delete
    suspend fun deleteMood(mood: MoodLog)

    @Query("SELECT * FROM mood_logs ORDER BY date DESC")
    fun getAllMoods(): Flow<List<MoodLog>>

    @Query("SELECT * FROM mood_logs WHERE date >= :startDate AND date <= :endDate ORDER BY date DESC")
    fun getMoodsByDateRange(startDate: Long, endDate: Long): Flow<List<MoodLog>>

    @Query("SELECT * FROM mood_logs WHERE date >= :date ORDER BY date ASC LIMIT 1")
    suspend fun getMoodByDate(date: Long): MoodLog?

    @Query("SELECT * FROM mood_logs WHERE id = :id")
    suspend fun getMoodById(id: Long): MoodLog?

    @Query("DELETE FROM mood_logs")
    suspend fun deleteAllMoods()

    // Get mood for today
    @Query("SELECT * FROM mood_logs WHERE date >= :startOfDay AND date <= :endOfDay LIMIT 1")
    suspend fun getTodayMood(startOfDay: Long, endOfDay: Long): MoodLog?

    // Get recent moods (last 7 days)
    @Query("SELECT * FROM mood_logs WHERE date >= :sevenDaysAgo ORDER BY date DESC")
    fun getRecentMoods(sevenDaysAgo: Long): Flow<List<MoodLog>>
}