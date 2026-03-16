package com.solarlyz.appsubastas.features.chat.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solarlyz.appsubastas.core.utils.Result
import com.solarlyz.appsubastas.features.chat.domain.model.Message
import com.solarlyz.appsubastas.features.chat.domain.repository.MessageRepository
import com.solarlyz.appsubastas.features.chat.domain.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val repository: MessageRepository
) : ViewModel() {

    private val _errorEvents = MutableSharedFlow<String>()
    val errorEvents = _errorEvents.asSharedFlow()

    val uiState: StateFlow<List<Message>> = repository.getMessages()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun sendMessage(text: String) {
        viewModelScope.launch {
            val result = sendMessageUseCase(text)
            if (result is Result.Error) {
                _errorEvents.emit(result.message)
            }
        }
    }
}
