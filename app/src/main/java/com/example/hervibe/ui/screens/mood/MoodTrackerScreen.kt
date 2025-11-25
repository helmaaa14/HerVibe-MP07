package com.example.hervibe.ui.screens.mood

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hervibe.data.local.entity.MoodLog
import com.example.hervibe.data.local.entity.MoodType
import com.example.hervibe.ui.components.AppCard
import com.example.hervibe.ui.components.AppTextField
import com.example.hervibe.ui.components.PrimaryButton
import com.example.hervibe.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoodTrackerScreen(
    onNavigateBack: () -> Unit,
    viewModel: MoodViewModel = viewModel()
) {
    val todayMood by viewModel.todayMood.collectAsState()
    val recentMoods by viewModel.recentMoods.collectAsState()

    var selectedMood by remember { mutableStateOf<MoodType?>(null) }
    var intensity by remember { mutableStateOf(3) }
    var notes by remember { mutableStateOf("") }
    var showSaveDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mood Tracker") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }

            // Today's Mood Status
            item {
                AppCard {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "How are you feeling today?",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        if (todayMood != null) {
                            Spacer(modifier = Modifier.height(16.dp))
                            val mood = MoodType.fromString(todayMood!!.moodType)
                            Text(
                                text = mood.emoji,
                                style = MaterialTheme.typography.displayLarge
                            )
                            Text(
                                text = mood.displayName,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Intensity: ${todayMood!!.intensity}/5",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            // Mood Selection Grid
            item {
                Text(
                    text = "Select Your Mood",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.height(280.dp)
                ) {
                    items(MoodType.values().toList()) { mood ->
                        MoodCard(
                            moodType = mood,
                            isSelected = selectedMood == mood,
                            onClick = { selectedMood = mood }
                        )
                    }
                }
            }

            // Intensity Slider
            item {
                AnimatedVisibility(visible = selectedMood != null) {
                    Column {
                        Text(
                            text = "Intensity: $intensity/5",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Slider(
                            value = intensity.toFloat(),
                            onValueChange = { intensity = it.toInt() },
                            valueRange = 1f..5f,
                            steps = 3
                        )
                    }
                }
            }

            // Notes Input
            item {
                AnimatedVisibility(visible = selectedMood != null) {
                    AppTextField(
                        value = notes,
                        onValueChange = { notes = it },
                        label = "Notes (Optional)",
                        placeholder = "How do you feel? Any thoughts?"
                    )
                }
            }

            // Save Button
            item {
                AnimatedVisibility(visible = selectedMood != null) {
                    PrimaryButton(
                        text = if (todayMood == null) "Save Mood" else "Update Mood",
                        onClick = { showSaveDialog = true },
                        icon = Icons.Default.Save
                    )
                }
            }

            // Recent Moods
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Recent Moods",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            items(recentMoods.take(7)) { mood ->
                RecentMoodCard(mood)
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }

        // Save Confirmation Dialog
        if (showSaveDialog && selectedMood != null) {
            AlertDialog(
                onDismissRequest = { showSaveDialog = false },
                title = { Text("Save Mood") },
                text = { Text("Save ${selectedMood!!.displayName} mood for today?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.saveMood(selectedMood!!, intensity, notes)
                            showSaveDialog = false
                            selectedMood = null
                            intensity = 3
                            notes = ""
                        }
                    ) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showSaveDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
fun MoodCard(
    moodType: MoodType,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected)
        MaterialTheme.colorScheme.primaryContainer
    else
        MaterialTheme.colorScheme.surface

    Card(
        onClick = onClick,
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 6.dp else 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = moodType.emoji,
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = moodType.displayName,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

@Composable
fun RecentMoodCard(mood: MoodLog) {
    val moodType = MoodType.fromString(mood.moodType)
    val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())

    AppCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = moodType.emoji,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(end = 16.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = moodType.displayName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = dateFormat.format(Date(mood.date)),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (mood.notes.isNotEmpty()) {
                    Text(
                        text = mood.notes,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2
                    )
                }
            }

            Text(
                text = "${mood.intensity}/5",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}