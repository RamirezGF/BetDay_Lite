package com.ramirezf.betdaylite.presentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onGoToProfile: () -> Unit,
    onBetClick: (String) -> Unit
) {
    val matches by viewModel.matches.collectAsState()

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
                MatchCard(
                    match = match,
                    onPick = { pick ->
                        val betId = viewModel.onBetClick(match, pick)
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Apuesta realizada ✅",
                                duration = SnackbarDuration.Long
                            )
                        }
                        onBetClick(betId)
                    }
                )
            }
        }
    }
}