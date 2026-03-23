package com.ramirezf.betdaylite.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ramirezf.betdaylite.presentation.detail.BetDetailScreen
import com.ramirezf.betdaylite.presentation.home.HomeScreen
import com.ramirezf.betdaylite.presentation.home.HomeViewModel
import com.ramirezf.betdaylite.presentation.profile.ProfileScreen
import com.ramirezf.betdaylite.presentation.profile.ProfileViewModel
import com.ramirezf.betdaylite.ui.navigation.screen.Screen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigation(
    homeViewModel: HomeViewModel,
    profileViewModel: ProfileViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = homeViewModel,
                onGoToProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                viewModel = profileViewModel,
                onBack = { navController.popBackStack() },
                onBetClick = { betId ->
                    navController.navigate(
                        Screen.BetDetail.createRoute(betId)
                    )
                }
            )
        }

        composable(
            Screen.BetDetail.route,
            arguments = listOf(navArgument("betId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val betId = backStackEntry.arguments?.getString("betId") ?: ""
            BetDetailScreen(
                betId = betId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}