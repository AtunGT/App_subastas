package com.solarlyz.appsubastas.features.subastas.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solarlyz.appsubastas.features.subastas.domain.entities.Auction
import com.solarlyz.appsubastas.features.subastas.presentation.screens.AuctionUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuctionViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(AuctionUIState())
    val uiState: StateFlow<AuctionUIState> = _uiState.asStateFlow()

    init {
        loadAuctions()
    }

    private fun loadAuctions() {
        viewModelScope.launch {

            _uiState.value = AuctionUIState(isLoading = true)

            val sampleAuctions = listOf(
                Auction(
                    id = "1",
                    title = "iPhone 15 Pro",
                    description = "Nuevo, sellado",
                    imageUrl = "https://via.placeholder.com/300",
                    currentPrice = 22000.0,
                    bids = 12,
                    timeRemaining = "2h 30m",
                    category = "Electrónica"
                ),
                Auction(
                    id = "2",
                    title = "MacBook Air M3",
                    description = "Excelente estado",
                    imageUrl = "https://via.placeholder.com/300",
                    currentPrice = 28000.0,
                    bids = 8,
                    timeRemaining = "5h 10m",
                    category = "Computación"
                )
            )

            _uiState.value = AuctionUIState(
                isLoading = false,
                auctions = sampleAuctions,
                error = null
            )
        }
    }
}