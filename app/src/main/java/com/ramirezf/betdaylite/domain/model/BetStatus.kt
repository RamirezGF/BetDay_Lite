package com.ramirezf.betdaylite.domain.model

enum class BetStatus {
    PENDING, WON, LOST
}

enum class Pick {
    HOME, DRAW, AWAY
}

data class Bet(
    val id: String,
    val matchId: String,
    val pick: Pick,
    val odd: Double,
    val stake: Double,
    val status: BetStatus
)
