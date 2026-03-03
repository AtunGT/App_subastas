package com.solarlyz.appsubastas.features.auction_management.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solarlyz.appsubastas.features.auction_management.data.datasources.remote.models.BidUpdate
import com.solarlyz.appsubastas.features.auction_management.domain.repositories.AuctionManagementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject
import kotlinx.serialization.json.Json

@HiltViewModel
class AuctionDetailViewModel @Inject constructor(
    private val repository: AuctionManagementRepository,
    private val okHttpClient: OkHttpClient
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuctionDetailState>(AuctionDetailState.Loading)
    val uiState: StateFlow<AuctionDetailState> = _uiState.asStateFlow()

    private var webSocket: WebSocket? = null

    fun loadAuctionDetails(id: Int) {
        viewModelScope.launch {
            _uiState.value = AuctionDetailState.Loading
            try {
                val auction = repository.getAuctionById(id)
                _uiState.value = AuctionDetailState.Success(auction)
                observeRealTimeBids(id)
            } catch (e: Exception) {
                _uiState.value = AuctionDetailState.Error("Error al obtener detalle")
            }
        }
    }

    private fun observeRealTimeBids(auctionId: Int) {
        val request = Request.Builder()
            .url("ws://18.211.229.66:8080/api/auctions/ws")
            .build()

        webSocket = okHttpClient.newWebSocket(request, object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                try {
                    val update = Json.decodeFromString<BidUpdate>(text)
                    if (update.auction_id == auctionId) {
                        updatePrice(update.new_price)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    private fun updatePrice(newPrice: Double) {
        _uiState.update { currentState ->
            if (currentState is AuctionDetailState.Success) {
                AuctionDetailState.Success(
                    currentState.auction.copy(currentPrice = newPrice)
                )
            } else {
                currentState
            }
        }
    }

    fun placeBid(auctionId: Int, amount: Double) {
        viewModelScope.launch {
            repository.placeBid(
                auctionId = auctionId,
                amount = amount,
                userId = 1
            ).fold(
                onSuccess = { },
                onFailure = {
                    _uiState.value = AuctionDetailState.Error("Error al realizar puja")
                }
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        webSocket?.close(1000, null)
    }
}