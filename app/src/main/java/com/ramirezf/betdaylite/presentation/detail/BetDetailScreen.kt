package com.ramirezf.betdaylite.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ramirezf.betdaylite.data.repository.BetRepositoryProvider

@Composable
fun BetDetailScreen(betId: String) {

    val context = LocalContext.current
    val repository = BetRepositoryProvider.provide(context)

    val viewModel = remember {
        BetDetailViewModel(repository)
    }

    val bet by viewModel.getBetById(betId).collectAsState(initial = null)

    if (bet == null) {
        Text("Cargando...")
        return
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Pick: ${bet!!.pick}")
        Text("Cuota: ${bet!!.odd}")
        Text("Estado: ${bet!!.status}")
    }
}