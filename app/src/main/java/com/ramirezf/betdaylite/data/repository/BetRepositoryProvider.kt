package com.ramirezf.betdaylite.data.repository

import android.content.Context
import com.ramirezf.betdaylite.data.local.JsonReader

object BetRepositoryProvider {

    private var repository: BetRepositoryImpl? = null

    fun provide(context: Context): BetRepositoryImpl {
        return repository ?: BetRepositoryImpl(JsonReader(context)).also {
            repository = it
        }
    }
}