package com.ramirezf.betdaylite.presentation.detail

import androidx.lifecycle.ViewModel
import com.ramirezf.betdaylite.data.local.BetEntity
import com.ramirezf.betdaylite.data.repository.BetRepositoryImpl
import com.ramirezf.betdaylite.domain.model.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class BetDetailViewModel(
    private val repository: BetRepositoryImpl
) : ViewModel() {

    fun getBetDetail(betId: String): Flow<Pair<BetEntity, Match>?> {
        return combine(repository.bets, repository.matches) { bets, matches ->
            val bet = bets.find { it.id == betId }
            val match = matches.find { it.id == bet?.matchId }
            if (bet != null && match != null) Pair(bet, match) else null
        }
    }
}