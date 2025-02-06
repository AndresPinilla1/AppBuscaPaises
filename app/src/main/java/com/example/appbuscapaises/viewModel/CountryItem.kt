package com.example.appbuscapaises.viewModel

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.appbuscapaises.modelo.Pais
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun CountryItem(country: Pais, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { navController.navigate("details/${country.name.common}") }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage( // Reemplaza rememberImagePainter
                model = country.flags.png,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(country.name.common)
                Text(text = "Capital: ${country.capital?.firstOrNull() ?: "N/A"}")
                Text("Población: ${country.population}")
            }
        }
    }
}

