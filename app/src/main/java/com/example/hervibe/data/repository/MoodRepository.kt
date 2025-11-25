package com.example.hervibe.data.repository

import com.example.hervibe.data.local.dao.MoodDao
import com.example.hervibe.data.local.entity.MoodLog
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

class MoodRepository(private val moodDao: MoodDao) {

    fun getAllMoods(): Flow<List<MoodLog>> = moodDao.getAllMoods()

    fun getMoodsByDateRange(startDate: Long, endDate: Long): Flow<List<MoodLog>> =
        moodDao.getMoodsByDateRange(startDate, endDate)

    suspend fun insertMood(mood: MoodLog): Long = moodDao.insertMood(mood)

    suspend fun updateMood(mood: MoodLog) = moodDao.updateMood(mood)

    suspend fun deleteMood(mood: MoodLog) = moodDao.deleteMood(mood)

    suspend fun getMoodById(id: Long): MoodLog? = moodDao.getMoodById(id)

    suspend fun deleteAllMoods() = moodDao.deleteAllMoods()

    // Get today's mood
    suspend fun getTodayMood(): MoodLog? {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startOfDay = calendar.timeInMillis

        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        val endOfDay = calendar.timeInMillis

        return moodDao.getTodayMood(startOfDay, endOfDay)
    }

    // Get recent moods (last 7 days)
    fun getRecentMoods(): Flow<List<MoodLog>> {
        val sevenDaysAgo = System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000)
        return moodDao.getRecentMoods(sevenDaysAgo)
    }
}