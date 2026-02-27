package com.solarlyz.appsubastas.features.subastas.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solarlyz.appsubastas.features.subastas.domain.usecases.GetAuctionsUseCase
import com.solarlyz.appsubastas.features.subastas.presentation.screens.AuctionUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuctionViewModel @Inject constructor(
    private val getAuctionsUseCase: GetAuctionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuctionUIState())
    val uiState: StateFlow<AuctionUIState> = _uiState.asStateFlow()

    init {
        loadAuctions()
    }

    private fun loadAuctions() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val result = getAuctionsUseCase()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    auctions = result,
                    error = null
                )
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error al cargar subastas", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}