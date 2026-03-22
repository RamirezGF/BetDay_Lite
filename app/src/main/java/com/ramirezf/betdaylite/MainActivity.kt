package com.ramirezf.betdaylite

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.ramirezf.betdaylite.data.repository.BetRepositoryProvider
import com.ramirezf.betdaylite.domain.usecase.GetBetsUseCase
import com.ramirezf.betdaylite.domain.usecase.GetMatchesUseCase
import com.ramirezf.betdaylite.domain.usecase.PlaceBetUseCase
import com.ramirezf.betdaylite.presentation.home.HomeViewModel
import com.ramirezf.betdaylite.presentation.profile.ProfileViewModel
import com.ramirezf.betdaylite.ui.navigation.MainNavigation

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = BetRepositoryProvider.provide(applicationContext)

        val homeViewModel = HomeViewModel(
            GetMatchesUseCase(repository),
            GetBetsUseCase(repository),
            PlaceBetUseCase(repository),
            repository
        )

        val profileViewModel = ProfileViewModel(
            GetBetsUseCase(repository),
            GetMatchesUseCase(repository)
        )

        setContent {
            MainNavigation(
                homeViewModel = homeViewModel,
                profileViewModel = profileViewModel
            )
        }
    }
}