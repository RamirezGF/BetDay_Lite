package com.ramirezf.betdaylite.domain.model

data class Match(
    val id: String,
    val startTime: String,
    val league: League,
    val homeTeam: Team,
    val awayTeam: Team,
    val market: Market
)

data class League(
    val name: String
)

data class Team(
    val name: String,
    val shortName: String
)

data class Market(
    val odds: Odds
)

data class Odds(
    val home: Double,
    val draw: Double,
    val away: Double
)
