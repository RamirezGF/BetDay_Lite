package com.ramirezf.betdaylite.domain.usecase

import com.ramirezf.betdaylite.data.repository.BetRepositoryImpl
import com.ramirezf.betdaylite.domain.model.Match
import com.ramirezf.betdaylite.domain.model.Pick

class PlaceBetUseCase(
    private val repository: BetRepositoryImpl
) {
    operator fun invoke(match: Match, pick: Pick): String {
        return repository.placeBet(match, pick)
    }
}