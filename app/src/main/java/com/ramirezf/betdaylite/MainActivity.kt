package com.ramirezf.betdaylite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramirezf.betdaylite.data.local.JsonReader
import com.ramirezf.betdaylite.data.repository.BetRepositoryImpl
import com.ramirezf.betdaylite.domain.usecase.GetMatchesUseCase
import com.ramirezf.betdaylite.domain.usecase.PlaceBetUseCase
import com.ramirezf.betdaylite.presentation.home.HomeViewModel
import com.ramirezf.betdaylite.ui.navigation.MainNavigation

class MainActivity : ComponentActivity() {
    private lateinit var repository: BetRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonReader = JsonReader(applicationContext)
        repository = BetRepositoryImpl(jsonReader)

        val homeViewModel = HomeViewModel(
            GetMatchesUseCase(repository),
            PlaceBetUseCase(repository),
            repository
        )

        setContent {
            MainNavigation(homeViewModel)
        }
    }
}
