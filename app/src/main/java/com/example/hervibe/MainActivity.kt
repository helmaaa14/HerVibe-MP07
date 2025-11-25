package com.example.hervibe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.hervibe.data.preferences.UserPreferences
import com.example.hervibe.ui.navigation.NavGraph
import com.example.hervibe.ui.navigation.Screen
import com.example.hervibe.ui.theme.HerVibeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }

            // Cek apakah user sudah login
            val userPreferences = remember { UserPreferences(this) }
            val isLoggedIn by userPreferences.isLoggedIn.collectAsState(initial = false)

            // Tentukan start destination berdasarkan status login
            val startDestination = if (isLoggedIn) Screen.Home.route else Screen.Login.route

            HerVibeTheme(darkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavGraph(
                        navController = navController,
                        startDestination = startDestination,
                        onThemeChange = {
                            isDarkTheme = !isDarkTheme
                        }
                    )
                }
            }
        }
    }
}