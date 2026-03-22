package com.ramirezf.betdaylite.presentation.profile

import androidx.lifecycle.ViewModel
import com.ramirezf.betdaylite.domain.usecase.GetBetsUseCase
import com.ramirezf.betdaylite.domain.usecase.GetMatchesUseCase

class ProfileViewModel(
    private val getBets: GetBetsUseCase,
    private val getMatches: GetMatchesUseCase
) : ViewModel() {

    val bets = getBets()
    val matches = getMatches()
}