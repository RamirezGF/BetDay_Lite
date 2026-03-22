package com.ramirezf.betdaylite.presentation.profile

import androidx.lifecycle.ViewModel
import com.ramirezf.betdaylite.domain.usecase.GetBetsUseCase

class ProfileViewModel(
    private val getBets: GetBetsUseCase
) : ViewModel() {

    val bets = getBets()
}