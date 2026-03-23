package com.ramirezf.betdaylite.domain.usecase

import com.ramirezf.betdaylite.data.local.BetEntity
import com.ramirezf.betdaylite.data.repository.BetRepositoryImpl
import kotlinx.coroutines.flow.Flow

class GetBetsUseCase(
    private val repository: BetRepositoryImpl
) {
    operator fun invoke(): Flow<List<BetEntity>> = repository.bets
}