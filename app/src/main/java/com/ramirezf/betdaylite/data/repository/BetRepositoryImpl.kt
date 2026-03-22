package com.ramirezf.betdaylite.data.repository

import com.ramirezf.betdaylite.data.local.JsonReader
import com.ramirezf.betdaylite.domain.model.Bet
import com.ramirezf.betdaylite.domain.model.BetStatus
import com.ramirezf.betdaylite.domain.model.Match
import com.ramirezf.betdaylite.domain.model.Pick
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class BetRepositoryImpl(
    private val jsonReader: JsonReader
) {

    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    val matches: StateFlow<List<Match>> = _matches

    private val _bets = MutableStateFlow<List<Bet>>(emptyList())
    val bets: StateFlow<List<Bet>> = _bets

    fun loadInitialData() {
        _matches.value = jsonReader.getMatches()
        _bets.value = emptyList()
    }

    fun placeBet(match: Match, pick: Pick): String {

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

        val newBet = Bet(
            id = UUID.randomUUID().toString(),
            matchId = match.id,
            pick = pick,
            odd = odd,
            stake = 10.0,
            status = status
        )

        _bets.value = _bets.value + newBet

        return newBet.id
    }
}