package com.ramirezf.betdaylite.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.ramirezf.betdaylite.presentation.components.MatchCard
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onGoToProfile: () -> Unit,
    viewModel: HomeViewModel
) {
    val matches by viewModel.matches.collectAsState()
    val bets by viewModel.bets.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text("BetDay Lite")
                },
                actions = {
                    IconButton(onClick = onGoToProfile) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Perfil"
                        )
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            contentPadding = padding
        ) {
            items(matches) { match ->
                val userBet = bets.find { it.matchId == match.id }
                val hasBet = userBet != null
                MatchCard(
                    match = match,
                    isAlreadyBet = hasBet,
                    selectedPick = userBet?.pick,
                    onPick = { pick ->
                        if (hasBet) {
                            scope.launch {
                                snackbarHostState.showSnackbar("Usted ya apostó en este juego")
                            }
                        } else {
                            viewModel.onBetClick(match, pick)
                            scope.launch {
                                snackbarHostState.showSnackbar("Apuesta realizada a ${pick.name} con éxito ✅")
                            }
                        }
                    }
                )
            }
        }
    }
}