package com.solarlyz.appsubastas.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solarlyz.appsubastas.domain.usecases.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message = _message.asStateFlow()

    fun onRegister(email: String, pass: String) {
        if (email.isEmpty() || pass.isEmpty()) {
            _message.value = "Por favor, completa todos los campos"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            try {
                registerUseCase(email, pass).collect {
                    _message.value = "Usuario creado correctamente"
                }
            } catch (e: Exception) {
                _message.value = "Error: ${e.message ?: "No se pudo crear la cuenta"}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearMessage() {
        _message.value = null
    }
}