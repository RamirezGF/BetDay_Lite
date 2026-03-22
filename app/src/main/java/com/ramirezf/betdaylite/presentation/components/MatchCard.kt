package com.ramirezf.betdaylite.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramirezf.betdaylite.domain.model.Match
import com.ramirezf.betdaylite.domain.model.Pick

@Composable
fun MatchCard(
    match: Match,
    onPick: (Pick) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(match.league.name, style = MaterialTheme.typography.labelMedium)

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                "${match.homeTeam.name} vs ${match.awayTeam.name}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                BetButton("1\n${match.market.odds.home}") {
                    onPick(Pick.HOME)
                }
                BetButton("X\n${match.market.odds.draw}") {
                    onPick(Pick.DRAW)
                }
                BetButton("2\n${match.market.odds.away}") {
                    onPick(Pick.AWAY)
                }
            }
        }
    }
}