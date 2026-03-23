package com.ramirezf.betdaylite.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.ramirezf.betdaylite.domain.model.Match
import com.ramirezf.betdaylite.domain.model.Pick
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MatchCard(
    match: Match,
    isAlreadyBet: Boolean,
    selectedPick: Pick?,
    onPick: (Pick) -> Unit
) {

    val formattedDate = formatMatchDate(match.startTime)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .graphicsLayer(alpha = if (isAlreadyBet) 0.6f else 1f),
        elevation = CardDefaults.cardElevation(defaultElevation = 7.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = match.league.name,
                    style = MaterialTheme.typography.labelMedium
                )

                Text(
                    text = "Fecha y hora de juego: $formattedDate",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                "${match.homeTeam.name} vs ${match.awayTeam.name}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                listOf(
                    "1" to match.market.odds.home to Pick.HOME,
                    "X" to match.market.odds.draw to Pick.DRAW,
                    "2" to match.market.odds.away to Pick.AWAY
                ).forEach { (label_odd, pickType) ->
                    val (label, odd) = label_odd
                    BetButton(
                        betType = label,
                        odds = odd.toString(),
                        enabled = !isAlreadyBet,
                        isSelected = isAlreadyBet && selectedPick == pickType,
                        modifier = Modifier.width(60.dp),
                        onClick = { onPick(pickType) }
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatMatchDate(dateString: String): String {
    val date = OffsetDateTime.parse(dateString)
    val formatter = DateTimeFormatter.ofPattern(
        "dd MMM - HH:mm",
        Locale.getDefault()
    )
    return date.format(formatter)
}