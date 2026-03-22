package com.ramirezf.betdaylite.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramirezf.betdaylite.data.repository.BetRepositoryImpl
import com.ramirezf.betdaylite.data.repository.BetRepositoryProvider
import com.ramirezf.betdaylite.domain.model.Bet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BetDetailViewModel(
    private val repository: BetRepositoryImpl
) : ViewModel() {

    fun getBetById(betId: String): Flow<Bet?> {
        return repository.bets.map { list ->
            list.find { it.id == betId }
        }
    }
}