package com.ramirezf.betdaylite.data.remote

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BetEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun betDao(): BetDao
}