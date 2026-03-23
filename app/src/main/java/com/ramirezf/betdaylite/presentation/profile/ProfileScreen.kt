package com.ramirezf.betdaylite.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.ramirezf.betdaylite.data.local.BetEntity
import com.ramirezf.betdaylite.domain.model.Bet
import com.ramirezf.betdaylite.domain.model.BetStatus
import com.ramirezf.betdaylite.domain.model.Pick
import com.ramirezf.betdaylite.presentation.components.BetCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onBetClick: (String) -> Unit,
    onBack: () -> Unit
) {

    val bets by viewModel.bets.collectAsState()
    val matches by viewModel.matches.collectAsState()


    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                color = Color(0xFFD32F2F),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            ) {
                                append("Mis ")
                            }
                            withStyle(style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )) {
                                append("Apuestas")
                            }
                        }
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { padding ->

        if (bets.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No tienes apuestas aún")
            }
        } else {
            LazyColumn(
                contentPadding = padding
            ) {
                items(bets) { bet ->
                    val match = matches.find { it.id == bet.matchId }
                    match?.let {
                        BetCard(
                            bet = bet.toDomain(),
                            match = it,
                            onClick = {
                                onBetClick(bet.id)
                            }
                        )
                    }
                }
            }
        }
    }
}

fun BetEntity.toDomain(): Bet = Bet(
    id = this.id,
    matchId = this.matchId,
    pick = Pick.valueOf(this.pick),
    odd = this.odd,
    stake = this.stake,
    status = BetStatus.valueOf(this.status)
)