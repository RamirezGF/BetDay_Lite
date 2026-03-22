package com.ramirezf.betdaylite.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ramirezf.betdaylite.presentation.components.BetCard

@Composable
fun ProfileScreen(
    onBetClick: (String) -> Unit,
    viewModel: ProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val bets by viewModel.bets.collectAsState()

    if (bets.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No tienes apuestas aún")
        }
    } else {
        LazyColumn {
            items(bets) { bet ->
                BetCard(
                    bet = bet,
                    onClick = {
                        onBetClick(bet.id)
                    }
                )
            }
        }
    }
}