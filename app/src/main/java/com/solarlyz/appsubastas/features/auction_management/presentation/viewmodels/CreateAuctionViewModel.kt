package com.solarlyz.appsubastas.features.auction_management.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solarlyz.appsubastas.features.auction_management.domain.usecases.CreateAuctionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateAuctionViewModel @Inject constructor(
    private val createAuctionUseCase: CreateAuctionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CreateAuctionState>(CreateAuctionState.Idle)
    val uiState: StateFlow<CreateAuctionState> = _uiState.asStateFlow()

    fun createAuction(
        title: String,
        description: String,
        initialPrice: Double,
        categoryId: Int,
        endDateInput: String
    ) {
        viewModelScope.launch {
            _uiState.value = CreateAuctionState.Loading

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            sdf.timeZone = TimeZone.getTimeZone("America/Mexico_City")
            val startDate = sdf.format(Date())

            val calendar = Calendar.getInstance()
            calendar.time = Date()
            calendar.add(Calendar.DAY_OF_MONTH, 7)

            val sdfEnd = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            sdfEnd.timeZone = TimeZone.getTimeZone("America/Mexico_City")
            val finalEndDate = sdfEnd.format(calendar.time)

            Log.d("CREATE_AUCTION_REQUEST", """
                title=$title
                description=$description
                initialPrice=$initialPrice
                categoryId=$categoryId
                startDate=$startDate
                endDate=$finalEndDate
            """.trimIndent())

            val result = createAuctionUseCase(
                title = title,
                description = description,
                initialPrice = initialPrice,
                startDate = startDate,
                endDate = finalEndDate,
                userId = 1,
                categoryId = categoryId
            )

            result.fold(
                onSuccess = {
                    Log.d("CREATE_AUCTION_SUCCESS", "Subasta creada correctamente")
                    _uiState.value = CreateAuctionState.Success
                },
                onFailure = {
                    Log.e("CREATE_AUCTION_ERROR", it.message ?: "Error")
                    _uiState.value = CreateAuctionState.Error(it.message ?: "Error inesperado")
                }
            )
        }
    }

    fun resetState() {
        _uiState.value = CreateAuctionState.Idle
    }
}

sealed class CreateAuctionState {
    object Idle : CreateAuctionState()
    object Loading : CreateAuctionState()
    object Success : CreateAuctionState()
    data class Error(val message: String) : CreateAuctionState()
}