package com.ramirezf.betdaylite.domain.usecase

import com.ramirezf.betdaylite.data.repository.BetRepositoryImpl

class GetMatchesUseCase(private val repo: BetRepositoryImpl) {
    operator fun invoke() = repo.matches
}