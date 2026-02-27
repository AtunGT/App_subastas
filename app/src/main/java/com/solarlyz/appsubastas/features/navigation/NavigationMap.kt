package com.solarlyz.appsubastas.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.solarlyz.appsubastas.features.auction_management.presentation.screens.AuctionDetailScreen
import com.solarlyz.appsubastas.features.auction_management.presentation.screens.CreateAuctionScreen
import com.solarlyz.appsubastas.features.subastas.presentation.screens.AuctionScreen

@Composable
fun NavigationMap() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "auctions") {

        composable("auctions") {
            AuctionScreen(
                onNavigateToCreate = { navController.navigate("create_auction") },
                onNavigateToDetail = { auctionId -> navController.navigate("auction_detail/$auctionId") }
            )
        }

        composable("create_auction") {
            CreateAuctionScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "auction_detail/{auctionId}",
            arguments = listOf(navArgument("auctionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val auctionId = backStackEntry.arguments?.getInt("auctionId") ?: 0
            AuctionDetailScreen(
                auctionId = auctionId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}