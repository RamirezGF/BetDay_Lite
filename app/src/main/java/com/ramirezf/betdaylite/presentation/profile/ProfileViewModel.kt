package com.ramirezf.betdaylite.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramirezf.betdaylite.data.local.BetEntity
import com.ramirezf.betdaylite.domain.model.Match
import com.ramirezf.betdaylite.domain.usecase.GetBetsUseCase
import com.ramirezf.betdaylite.domain.usecase.GetMatchesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel(
    private val getBets: GetBetsUseCase,
    private val getMatches: GetMatchesUseCase
) : ViewModel() {

    val bets: StateFlow<List<BetEntity>> = getBets()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val matches: StateFlow<List<Match>> = getMatches()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}