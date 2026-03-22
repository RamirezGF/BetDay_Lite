package com.ramirezf.betdaylite.ui.navigation.screen

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Profile : Screen("profile")
    data object BetDetail : Screen("bet_detail/{betId}") {
        fun createRoute(betId: String) = "bet_detail/$betId"
    }
}