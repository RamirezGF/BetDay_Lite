package com.ramirezf.betdaylite.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bets")
data class BetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val matchId: String,
    val homeTeam: String,
    val awayTeam: String,
    val selection: String,
    val odd: Double,
    val status: String
)