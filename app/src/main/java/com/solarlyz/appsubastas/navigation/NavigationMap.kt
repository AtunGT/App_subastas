package com.solarlyz.appsubastas.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.solarlyz.appsubastas.presentation.screens.LoginScreen
import com.solarlyz.appsubastas.presentation.screens.RegisterScreen
import com.solarlyz.appsubastas.presentation.viewmodels.LoginViewModel
import com.solarlyz.appsubastas.presentation.viewmodels.RegisterViewModel
import androidx.hilt.navigation.compose.hiltViewModel

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    composable(AppRoute.Login.route) {
        val viewModel: LoginViewModel = hiltViewModel()
        LoginScreen(
            viewModel = viewModel,
            onNavigateToRegister = { navController.navigate(AppRoute.Register.route) },
            onLoginSuccess = {
                navController.navigate(AppRoute.Auctions.route) {
                    popUpTo(AppRoute.Login.route) { inclusive = true }
                }
            }
        )
    }
    composable(AppRoute.Register.route) {
        val viewModel: RegisterViewModel = hiltViewModel()
        RegisterScreen(
            viewModel = viewModel,
            onNavigateBack = { navController.popBackStack() }
        )
    }
}