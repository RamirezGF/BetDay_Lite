package com.ramirezf.betdaylite.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BetDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bet: BetEntity)

    @Query("SELECT * FROM bets ORDER BY placedAt DESC")
    fun getAll(): Flow<List<BetEntity>>

    @Query("SELECT * FROM bets WHERE matchId = :matchId LIMIT 1")
    suspend fun getByMatchId(matchId: String): BetEntity?
}