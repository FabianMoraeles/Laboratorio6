package com.example.laboratorio6

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

// Definición de la clase `Place`
data class Place(val id: String, val name: String, val location: String)

// Lista de conciertos para usar en el composable
val concertList = listOf(
    Place("1", "Concierto de Rock", "Estadio Nacional"),
    Place("2", "Festival de Jazz", "Parque Central"),
    Place("3", "Música Clásica", "Teatro Municipal")
)

// Composable que muestra la lista de conciertos
@Composable
fun ConcertListScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(concertList) { concert ->
            ConcertCard(concert)
        }
    }
}

// Composable para cada tarjeta de concierto
@Composable
fun ConcertCard(concert: Place) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = concert.name,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = concert.location,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}
