package com.ramirezf.betdaylite.data.repository

import com.ramirezf.betdaylite.data.local.AppDatabase
import com.ramirezf.betdaylite.data.local.BetEntity
import com.ramirezf.betdaylite.data.local.JsonReader
import com.ramirezf.betdaylite.domain.model.BetStatus
import com.ramirezf.betdaylite.domain.model.Match
import com.ramirezf.betdaylite.domain.model.Pick
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class BetRepositoryImpl(
    private val jsonReader: JsonReader,
    private val db: AppDatabase
) {
    private val dao = db.betDao()
    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    val matches: StateFlow<List<Match>> = _matches
    val bets: Flow<List<BetEntity>> = dao.getAll()

    fun loadInitialData() {
        _matches.value = jsonReader.getMatches()
    }

    suspend fun placeBet(match: Match, pick: Pick): String {
        val existing = dao.getByMatchId(match.id)
        if (existing != null) return "Ya apostaste en este partido"

        val odd = when (pick) {
            Pick.HOME -> match.market.odds.home
            Pick.DRAW -> match.market.odds.draw
            Pick.AWAY -> match.market.odds.away
        }

        val status = listOf(
            BetStatus.WON,
            BetStatus.LOST,
            BetStatus.PENDING
        ).random()

        val entity = BetEntity(
            id = UUID.randomUUID().toString(),
            matchId = match.id,
            homeTeam = match.homeTeam.name,
            awayTeam = match.awayTeam.name,
            pick = pick.name,
            odd = odd,
            stake = 10.0,
            status = status.name
        )

        dao.insert(entity)
        return "Apuesta realizada a ${pick.name} con éxito ✅"
    }
}