package com.ramirezf.betdaylite.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ramirezf.betdaylite.domain.model.Bet
import com.ramirezf.betdaylite.domain.model.BetStatus
import com.ramirezf.betdaylite.domain.model.Match

@Composable
fun BetCard(bet: Bet, match: Match, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick()}
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 7.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Liga: ${match.league.name}",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Equipos: ${match.homeTeam.name} vs ${match.awayTeam.name}",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Pick: ${bet.pick}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Cuota: ${bet.odd}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Estado: ${bet.status}",
                color = getStatusColor(bet.status),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

fun getStatusColor(status: BetStatus): Color {
    return when (status) {
        BetStatus.PENDING -> Color(0xFFD3BF01)
        BetStatus.WON -> Color(0xFF00D70B)
        BetStatus.LOST -> Color(0xFFFF0000)
    }
}