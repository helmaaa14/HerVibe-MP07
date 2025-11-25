package com.example.hervibe.data.repository

import com.example.hervibe.data.local.dao.CycleDao
import com.example.hervibe.data.local.entity.CycleLog
import kotlinx.coroutines.flow.Flow

class CycleRepository(private val cycleDao: CycleDao) {

    fun getAllCycles(): Flow<List<CycleLog>> = cycleDao.getAllCycles()

    fun getCyclesByDateRange(startDate: Long, endDate: Long): Flow<List<CycleLog>> =
        cycleDao.getCyclesByDateRange(startDate, endDate)

    suspend fun insertCycle(cycle: CycleLog): Long = cycleDao.insertCycle(cycle)

    suspend fun updateCycle(cycle: CycleLog) = cycleDao.updateCycle(cycle)

    suspend fun deleteCycle(cycle: CycleLog) = cycleDao.deleteCycle(cycle)

    suspend fun getLatestCycle(): CycleLog? = cycleDao.getLatestCycle()

    suspend fun getRecentCycles(limit: Int = 3): List<CycleLog> =
        cycleDao.getRecentCycles(limit)

    suspend fun deleteAllCycles() = cycleDao.deleteAllCycles()

    suspend fun getCycleForDate(date: Long): CycleLog? = cycleDao.getCycleForDate(date)

    // Calculate average cycle length from recent cycles
    suspend fun calculateAverageCycleLength(): Int {
        val recentCycles = getRecentCycles(3)
        if (recentCycles.size < 2) return 28 // Default 28 days

        val cycleLengths = mutableListOf<Int>()
        for (i in 0 until recentCycles.size - 1) {
            val current = recentCycles[i]
            val previous = recentCycles[i + 1]
            val daysDiff = ((current.startDate - previous.startDate) / (1000 * 60 * 60 * 24)).toInt()
            cycleLengths.add(daysDiff)
        }

        return if (cycleLengths.isNotEmpty()) {
            cycleLengths.average().toInt()
        } else {
            28
        }
    }

    // Predict next period date
    suspend fun predictNextPeriod(): Long? {
        val latestCycle = getLatestCycle() ?: return null
        val avgLength = calculateAverageCycleLength()

        return latestCycle.startDate + (avgLength * 24 * 60 * 60 * 1000L)
    }
}