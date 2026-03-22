package com.ramirezf.betdaylite.domain.usecase

import com.ramirezf.betdaylite.data.repository.BetRepositoryImpl

class GetBetsUseCase(
    private val repository: BetRepositoryImpl
) {
    operator fun invoke() = repository.bets
}