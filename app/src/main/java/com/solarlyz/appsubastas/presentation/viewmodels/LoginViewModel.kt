package com.solarlyz.appsubastas.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solarlyz.appsubastas.domain.usecases.LoginUseCase
import com.solarlyz.appsubastas.core.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object LoginSuccess : UiEvent()
    }

    fun onLogin(email: String, pass: String) {
        viewModelScope.launch {
            loginUseCase(email, pass).collect { result ->
                when (result) {
                    is Result.Loading -> { _isLoading.value = true }
                    is Result.Success -> {
                        _isLoading.value = false
                        _eventFlow.emit(UiEvent.LoginSuccess)
                    }
                    is Result.Error -> {
                        _isLoading.value = false
                        _eventFlow.emit(UiEvent.ShowSnackbar(result.message ?: "Error al entrar"))
                    }
                }
            }
        }
    }
}