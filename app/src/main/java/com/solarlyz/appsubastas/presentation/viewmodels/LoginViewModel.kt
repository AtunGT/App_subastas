package com.solarlyz.appsubastas.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solarlyz.appsubastas.domain.usecases.LoginUseCase
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
            _isLoading.value = true
            loginUseCase(email, pass).collect { result ->
                _isLoading.value = false
                result.onSuccess {
                    _eventFlow.emit(UiEvent.LoginSuccess)
                }
                result.onFailure { exception ->
                    _eventFlow.emit(UiEvent.ShowSnackbar(exception.message ?: "Error desconocido"))
                }
            }
        }
    }
}