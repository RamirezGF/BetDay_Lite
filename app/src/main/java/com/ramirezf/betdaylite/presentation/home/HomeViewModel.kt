package com.ramirezf.betdaylite.presentation.home

import androidx.lifecycle.ViewModel
import com.ramirezf.betdaylite.data.repository.BetRepositoryImpl
import com.ramirezf.betdaylite.domain.model.Match
import com.ramirezf.betdaylite.domain.model.Pick
import com.ramirezf.betdaylite.domain.usecase.GetMatchesUseCase
import com.ramirezf.betdaylite.domain.usecase.PlaceBetUseCase

class HomeViewModel(
    private val getMatches: GetMatchesUseCase,
    private val placeBet: PlaceBetUseCase,
    private val repository: BetRepositoryImpl
) : ViewModel() {

    val matches = getMatches()

    init {
        repository.loadInitialData()
    }

    fun onBetClick(match: Match, pick: Pick): String {
        return placeBet(match, pick)
    }
}