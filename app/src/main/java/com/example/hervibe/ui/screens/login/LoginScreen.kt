package com.example.hervibe.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hervibe.R
import com.example.hervibe.data.preferences.UserPreferences
import com.example.hervibe.ui.components.AppTextField
import com.example.hervibe.ui.components.PrimaryButton
import com.example.hervibe.ui.components.SecondaryButton
import com.example.hervibe.ui.theme.GradientPinkEnd
import com.example.hervibe.ui.theme.GradientPinkStart
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val scope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLogin by remember { mutableStateOf(true) }
    var name by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        GradientPinkStart.copy(alpha = 0.3f),
                        GradientPinkEnd.copy(alpha = 0.1f),
                        Color.White
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Logo/Illustration Area
            // Ganti R.drawable.logo dengan logo Anda sendiri
            // Untuk sementara menggunakan icon
            Surface(
                modifier = Modifier.size(120.dp),
                shape = MaterialTheme.shapes.extraLarge,
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(24.dp)
                ) {
                    // Uncomment ini dan ganti dengan logo Anda:
                    // Image(
                    //     painter = painterResource(id = R.drawable.logo),
                    //     contentDescription = "HerVibe Logo",
                    //     modifier = Modifier.fillMaxSize()
                    // )

                    // Placeholder icon (hapus setelah punya logo)
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(60.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Welcome Text
            Text(
                text = if (isLogin) "Welcome Back!" else "Welcome To",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "HerVibe",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Form Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    // Register: Name field
                    if (!isLogin) {
                        AppTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = "Full Name",
                            placeholder = "Enter your name",
                            leadingIcon = Icons.Default.Person,
                            keyboardType = KeyboardType.Text
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Email field
                    AppTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Email",
                        placeholder = "Enter your email",
                        leadingIcon = Icons.Default.Email,
                        keyboardType = KeyboardType.Email
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Password field
                    AppTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = "Password",
                        placeholder = "Enter your password",
                        leadingIcon = Icons.Default.Lock,
                        isPassword = true
                    )

                    if (isLogin) {
                        Spacer(modifier = Modifier.height(8.dp))
                        TextButton(
                            onClick = { /* TODO: Forgot password */ },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text(
                                text = "Forgot Password?",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Login/Register Button
                    PrimaryButton(
                        text = if (isLogin) "Let's Start →" else "Create Account",
                        onClick = {
                            // Validasi input
                            if (email.isNotEmpty() && password.isNotEmpty()) {
                                if (!isLogin && name.isEmpty()) {
                                    // Jika register tapi nama kosong
                                    return@PrimaryButton
                                }

                                // Simpan data user
                                scope.launch {
                                    val userName = if (isLogin) {
                                        // Jika login, ambil nama dari email (sebelum @)
                                        email.substringBefore("@").replaceFirstChar { it.uppercase() }
                                    } else {
                                        // Jika register, pakai nama yang diinput
                                        name
                                    }

                                    userPreferences.saveUserData(
                                        name = userName,
                                        email = email
                                    )
                                    onLoginSuccess()
                                }
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Toggle Login/Register
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isLogin) "Don't have an account?" else "Already have an account?",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        TextButton(onClick = { isLogin = !isLogin }) {
                            Text(
                                text = if (isLogin) "Sign Up" else "Login",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}