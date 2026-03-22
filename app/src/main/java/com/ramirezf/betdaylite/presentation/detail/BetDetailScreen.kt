package com.ramirezf.betdaylite.presentation.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramirezf.betdaylite.data.repository.BetRepositoryProvider
import com.ramirezf.betdaylite.domain.model.Match
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BetDetailScreen(betId: String, onBack: () -> Unit) {
    val context = LocalContext.current
    val repository = BetRepositoryProvider.provide(context)
    val viewModel = remember { BetDetailViewModel(repository) }

    val detail by viewModel.getBetDetail(betId).collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Apuesta") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                }
            )
        }
    ) { padding ->
        detail?.let { (bet, match) ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Sección: Info del Evento
                Text("EVENTO", style = MaterialTheme.typography.labelLarge, color = Color.Gray)
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(match.league.name, style = MaterialTheme.typography.labelMedium)
                        Text(
                            "${match.homeTeam.name} vs ${match.awayTeam.name}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text("Inicio: ${formatMatchDate(match.startTime)}", style = MaterialTheme.typography.bodySmall)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text("TU APUESTA", style = MaterialTheme.typography.labelLarge, color = Color.Gray)
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FA))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        DetailRow("Mercado", "Resultado Final (1X2)")
                        DetailRow("Tu Pick", formatPickName(bet.pick.toString(), match))
                        DetailRow("Cuota", "x${bet.odd}")
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            thickness = DividerDefaults.Thickness,
                            color = DividerDefaults.color
                        )
                        DetailRow("Monto Apostado", "S/ ${bet.stake}")
                        DetailRow(
                            label = "Retorno Potencial",
                            value = "S/ ${bet.stake * bet.odd}",
                            isBold = true
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Badge de Estado dinámico
                        StatusBadge(bet.status.toString())
                    }
                }

                Text(
                    "ID de transacción: ${bet.id}",
                    modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.LightGray
                )
            }
        } ?: Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun DetailRow(label: String, value: String, isBold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray)
        Text(value, fontWeight = if (isBold) FontWeight.ExtraBold else FontWeight.Normal)
    }
}

@Composable
fun StatusBadge(status: String) {
    val (color, label) = when (status) {
        "WON" -> Color(0xFF4CAF50) to "GANADA"
        "LOST" -> Color(0xFFF44336) to "PERDIDA"
        else -> Color(0xFFFFC107) to "PENDIENTE"
    }

    Surface(
        color = color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color)
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            color = color,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

fun formatPickName(pick: String, match: Match): String {
    return when(pick) {
        "HOME" -> match.homeTeam.name
        "AWAY" -> match.awayTeam.name
        "DRAW" -> "Empate"
        else -> pick
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