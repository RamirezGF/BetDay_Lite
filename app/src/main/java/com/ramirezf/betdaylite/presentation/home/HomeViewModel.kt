package com.ramirezf.betdaylite.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramirezf.betdaylite.data.local.BetEntity
import com.ramirezf.betdaylite.data.repository.BetRepositoryImpl
import com.ramirezf.betdaylite.domain.model.Match
import com.ramirezf.betdaylite.domain.model.Pick
import com.ramirezf.betdaylite.domain.usecase.GetBetsUseCase
import com.ramirezf.betdaylite.domain.usecase.GetMatchesUseCase
import com.ramirezf.betdaylite.domain.usecase.PlaceBetUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMatches: GetMatchesUseCase,
    private val getBets: GetBetsUseCase,
    private val placeBet: PlaceBetUseCase,
    private val repository: BetRepositoryImpl
) : ViewModel() {
    val matches: StateFlow<List<Match>> = repository.matches
    val bets: StateFlow<List<BetEntity>> = getBets()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    init {
        repository.loadInitialData()
    }
    fun onBetClick(match: Match, pick: Pick, onResult: (String) -> Unit) {
        viewModelScope.launch {
            val result = placeBet(match, pick)
            onResult(result)
        }
    }
}