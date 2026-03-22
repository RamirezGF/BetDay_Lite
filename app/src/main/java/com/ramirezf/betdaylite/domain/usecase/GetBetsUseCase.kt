package com.ramirezf.betdaylite.domain.usecase

import com.ramirezf.betdaylite.data.repository.BetRepositoryImpl

class GetBetsUseCase(private val repo: BetRepositoryImpl) {
    operator fun invoke() = repo.bets
}