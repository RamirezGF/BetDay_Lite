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

@Composable
fun BetCard(bet: Bet,onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick()}
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            // 🔹 Equipos (si luego quieres mapear match → mejor aún)
            Text(
                text = "Match ID: ${bet.matchId}",
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            // 🔹 Selección
            Text(
                text = "Pick: ${bet.pick}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            // 🔹 Cuota
            Text(
                text = "Cuota: ${bet.odd}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            // 🔹 Estado
            Text(
                text = "Estado: ${bet.status}",
                color = getStatusColor(bet.status),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

fun getStatusColor(status: BetStatus): Color {
    return when (status) {
        BetStatus.PENDING -> Color.Gray
        BetStatus.WON -> Color(0xFF2E7D32)   // Verde
        BetStatus.LOST -> Color(0xFFC62828)  // Rojo
    }
}