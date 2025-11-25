package com.example.hervibe.data.local.dao

import androidx.room.*
import com.example.hervibe.data.local.entity.CycleLog
import kotlinx.coroutines.flow.Flow

@Dao
interface CycleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCycle(cycle: CycleLog): Long

    @Update
    suspend fun updateCycle(cycle: CycleLog)

    @Delete
    suspend fun deleteCycle(cycle: CycleLog)

    @Query("SELECT * FROM cycle_logs ORDER BY startDate DESC")
    fun getAllCycles(): Flow<List<CycleLog>>

    @Query("SELECT * FROM cycle_logs WHERE startDate >= :startDate AND startDate <= :endDate")
    fun getCyclesByDateRange(startDate: Long, endDate: Long): Flow<List<CycleLog>>

    @Query("SELECT * FROM cycle_logs ORDER BY startDate DESC LIMIT 1")
    suspend fun getLatestCycle(): CycleLog?

    @Query("SELECT * FROM cycle_logs ORDER BY startDate DESC LIMIT :limit")
    suspend fun getRecentCycles(limit: Int = 3): List<CycleLog>

    @Query("DELETE FROM cycle_logs")
    suspend fun deleteAllCycles()

    // Get cycle that contains a specific date
    @Query("SELECT * FROM cycle_logs WHERE startDate <= :date AND (endDate >= :date OR endDate IS NULL) LIMIT 1")
    suspend fun getCycleForDate(date: Long): CycleLog?
}