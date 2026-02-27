package com.solarlyz.appsubastas.features.auction_management.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.solarlyz.appsubastas.features.auction_management.presentation.viewmodels.AuctionDetailState
import com.solarlyz.appsubastas.features.auction_management.presentation.viewmodels.AuctionDetailViewModel

@Composable
fun AuctionDetailScreen(
    auctionId: Int,
    onNavigateBack: () -> Unit,
    viewModel: AuctionDetailViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(auctionId) {
        viewModel.loadAuctionDetails(auctionId)
    }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            when (val state = uiState) {

                is AuctionDetailState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is AuctionDetailState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is AuctionDetailState.Success -> {

                    val auction = state.auction
                    var bidAmount by remember { mutableStateOf("") }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        AsyncImage(
                            model = auction.imageUrl,
                            contentDescription = auction.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = auction.title,
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Text(
                            text = "Categoría: ${auction.category}",
                            style = MaterialTheme.typography.labelLarge
                        )

                        Text(
                            text = "Precio Actual: $${auction.currentPrice}",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            text = "Precio Inicial: $${auction.initialPrice}",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Text(
                            text = "Inicia: ${auction.startDate}",
                            style = MaterialTheme.typography.bodySmall
                        )

                        Text(
                            text = "Termina: ${auction.endDate}",
                            style = MaterialTheme.typography.bodySmall
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                        Text(
                            text = "Descripción",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = auction.description,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = bidAmount,
                            onValueChange = { bidAmount = it },
                            label = { Text("Monto de puja") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Button(
                            onClick = {
                                val amount = bidAmount.toDoubleOrNull() ?: 0.0
                                viewModel.placeBid(auctionId, amount)
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Pujar")
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = onNavigateBack,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Volver")
                        }
                    }
                }
            }
        }
    }
}