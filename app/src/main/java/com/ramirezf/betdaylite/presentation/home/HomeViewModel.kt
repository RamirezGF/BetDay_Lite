package com.ramirezf.betdaylite.presentation.home

import androidx.lifecycle.ViewModel
import com.ramirezf.betdaylite.data.repository.BetRepositoryImpl
import com.ramirezf.betdaylite.domain.model.Bet
import com.ramirezf.betdaylite.domain.model.Match
import com.ramirezf.betdaylite.domain.model.Pick
import com.ramirezf.betdaylite.domain.usecase.GetBetsUseCase
import com.ramirezf.betdaylite.domain.usecase.GetMatchesUseCase
import com.ramirezf.betdaylite.domain.usecase.PlaceBetUseCase
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val getMatches: GetMatchesUseCase,
    private val getBets: GetBetsUseCase,
    private val placeBet: PlaceBetUseCase,
    private val repository: BetRepositoryImpl
) : ViewModel() {

    val matches: StateFlow<List<Match>> = repository.matches
    val bets: StateFlow<List<Bet>> = repository.bets

    init {
        repository.loadInitialData()
    }

    fun onBetClick(match: Match, pick: Pick): String {
        return placeBet(match, pick)
    }
}