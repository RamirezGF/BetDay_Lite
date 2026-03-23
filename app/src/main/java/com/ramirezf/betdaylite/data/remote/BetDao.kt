package com.ramirezf.betdaylite.data.remote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BetDao {

    @Insert
    suspend fun insert(bet: BetEntity)

    @Query("SELECT * FROM bets")
    fun getAll(): Flow<List<BetEntity>>
}

