package com.solarlyz.appsubastas.features.auction_management.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solarlyz.appsubastas.features.auction_management.domain.repositories.AuctionManagementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuctionDetailViewModel @Inject constructor(
    private val repository: AuctionManagementRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<AuctionDetailState>(AuctionDetailState.Loading)

    val uiState: StateFlow<AuctionDetailState> = _uiState.asStateFlow()

    fun loadAuctionDetails(id: Int) {
        viewModelScope.launch {
            _uiState.value = AuctionDetailState.Loading
            try {
                val auction = repository.getAuctionById(id)
                _uiState.value = AuctionDetailState.Success(auction)
            } catch (e: Exception) {
                _uiState.value =
                    AuctionDetailState.Error("Error al obtener detalle")
            }
        }
    }

    fun placeBid(auctionId: Int, amount: Double) {
        viewModelScope.launch {
            val result = repository.placeBid(
                auctionId = auctionId,
                amount = amount,
                userId = 1
            )

            result.fold(
                onSuccess = {
                    loadAuctionDetails(auctionId)
                },
                onFailure = {
                    _uiState.value =
                        AuctionDetailState.Error("Error al realizar puja")
                }
            )
        }
    }
}