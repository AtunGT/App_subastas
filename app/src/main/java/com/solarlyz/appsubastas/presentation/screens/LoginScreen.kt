package com.solarlyz.appsubastas.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.solarlyz.appsubastas.presentation.viewmodels.LoginViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isLoading by viewModel.isLoading.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is LoginViewModel.UiEvent.LoginSuccess -> onLoginSuccess()
                is LoginViewModel.UiEvent.ShowSnackbar -> {
                    android.widget.Toast.makeText(context, event.message, android.widget.Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Iniciar Sesión", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        } else {
            Button(
                onClick = { viewModel.onLogin(email, password) },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text("Entrar")
            }
        }

        TextButton(onClick = onNavigateToRegister, enabled = !isLoading) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}