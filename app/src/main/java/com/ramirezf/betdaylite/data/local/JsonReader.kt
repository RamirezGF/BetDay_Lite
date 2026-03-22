package com.ramirezf.betdaylite.data.local

import android.content.Context
import com.google.gson.Gson
import com.ramirezf.betdaylite.domain.model.Bet
import com.ramirezf.betdaylite.domain.model.Match

class JsonReader(private val context: Context) {

    private val gson = Gson()

    fun getMatches(): List<Match> {
        val json = context.assets.open("matches.json")
            .bufferedReader()
            .use { it.readText() }

        val wrapper = gson.fromJson(json, MatchesWrapper::class.java)
        return wrapper.matches
    }

    fun getBets(): List<Bet> {
        val json = context.assets.open("bets.json")
            .bufferedReader()
            .use { it.readText() }

        val wrapper = gson.fromJson(json, BetsWrapper::class.java)
        return wrapper.bets
    }
}

data class MatchesWrapper(val matches: List<Match>)
data class BetsWrapper(val bets: List<Bet>)