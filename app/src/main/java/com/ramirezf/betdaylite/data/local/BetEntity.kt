package com.ramirezf.betdaylite.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bets")
data class BetEntity(
    @PrimaryKey
    val id: String,
    val matchId: String,
    val homeTeam: String,
    val awayTeam: String,
    val pick: String,
    val odd: Double,
    val stake: Double,
    val status: String,
    val placedAt: Long = System.currentTimeMillis()
)