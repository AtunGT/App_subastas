package com.solarlyz.appsubastas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.solarlyz.appsubastas.navigation.AppRoute
import com.solarlyz.appsubastas.navigation.authGraph
import com.solarlyz.appsubastas.features.auctions.presentation.screens.AuctionsScreen
import com.solarlyz.appsubastas.ui.theme.AppsubastasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppsubastasTheme {
                val navController = rememberNavController()
                Scaffold { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = AppRoute.Login.route,
                        modifier = Modifier.padding(padding)
                    ) {
                        authGraph(navController)
                        composable(AppRoute.Auctions.route) {
                            AuctionsScreen()
                        }
                    }
                }
            }
        }
    }
}