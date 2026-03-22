package com.ramirezf.betdaylite.domain.usecase

import com.ramirezf.betdaylite.data.repository.BetRepositoryImpl
import com.ramirezf.betdaylite.domain.model.Match
import com.ramirezf.betdaylite.domain.model.Pick

class PlaceBetUseCase(private val repo: BetRepositoryImpl) {
    operator fun invoke(match: Match, pick: Pick) {
        repo.placeBet(match, pick)
    }
}