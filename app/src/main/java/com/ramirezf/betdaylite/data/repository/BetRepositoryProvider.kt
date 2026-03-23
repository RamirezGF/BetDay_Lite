package com.ramirezf.betdaylite.data.repository

import android.content.Context
import com.ramirezf.betdaylite.data.local.AppDatabase
import com.ramirezf.betdaylite.data.local.JsonReader

object BetRepositoryProvider {

    @Volatile private var repository: BetRepositoryImpl? = null

    fun provide(context: Context): BetRepositoryImpl {
        return repository ?: synchronized(this) {
            BetRepositoryImpl(
                jsonReader = JsonReader(context),
                db = AppDatabase.getInstance(context)
            ).also { repository = it }
        }
    }
}