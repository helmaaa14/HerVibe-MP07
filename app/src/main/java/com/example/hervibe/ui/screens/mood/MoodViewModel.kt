package com.example.hervibe.ui.screens.mood

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.hervibe.data.local.database.AppDatabase
import com.example.hervibe.data.local.entity.MoodLog
import com.example.hervibe.data.local.entity.MoodType
import com.example.hervibe.data.repository.MoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoodViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MoodRepository

    private val _todayMood = MutableStateFlow<MoodLog?>(null)
    val todayMood: StateFlow<MoodLog?> = _todayMood.asStateFlow()

    private val _recentMoods = MutableStateFlow<List<MoodLog>>(emptyList())
    val recentMoods: StateFlow<List<MoodLog>> = _recentMoods.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        val moodDao = AppDatabase.getDatabase(application).moodDao()
        repository = MoodRepository(moodDao)
        loadTodayMood()
        loadRecentMoods()
    }

    private fun loadTodayMood() {
        viewModelScope.launch {
            _isLoading.value = true
            _todayMood.value = repository.getTodayMood()
            _isLoading.value = false
        }
    }

    private fun loadRecentMoods() {
        viewModelScope.launch {
            repository.getRecentMoods().collect { moods ->
                _recentMoods.value = moods
            }
        }
    }

    fun saveMood(moodType: MoodType, intensity: Int, notes: String = "") {
        viewModelScope.launch {
            val mood = MoodLog(
                date = System.currentTimeMillis(),
                moodType = moodType.name,
                intensity = intensity,
                notes = notes
            )
            repository.insertMood(mood)
            loadTodayMood()
            loadRecentMoods()
        }
    }

    fun updateMood(moodLog: MoodLog) {
        viewModelScope.launch {
            repository.updateMood(moodLog)
            loadTodayMood()
            loadRecentMoods()
        }
    }

    fun deleteMood(moodLog: MoodLog) {
        viewModelScope.launch {
            repository.deleteMood(moodLog)
            loadTodayMood()
            loadRecentMoods()
        }
    }
}