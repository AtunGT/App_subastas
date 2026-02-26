package com.solarlyz.appsubastas.navigation

sealed class AppRoute(val route: String) {
    object Login : AppRoute("login")
    object Register : AppRoute("register")
    object Auctions : AppRoute("AuctionsScreen")
}