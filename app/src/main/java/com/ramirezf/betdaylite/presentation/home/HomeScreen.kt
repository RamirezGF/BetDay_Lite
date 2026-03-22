package com.ramirezf.betdaylite.presentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ramirezf.betdaylite.presentation.components.MatchCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onGoToProfile: () -> Unit,
    onBetClick: (String) -> Unit
) {
    val matches by viewModel.matches.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("BetDay Lite")
                }
            )
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(matches) { match ->
                MatchCard(
                    match = match,
                    onPick = { pick -> viewModel.onBetClick(match, pick) }
                )
            }
        }
    }
}