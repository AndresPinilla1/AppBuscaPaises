package com.example.appbuscapaises.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appbuscapaises.viewModel.CountryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(navController: NavController, viewModel: CountryViewModel) {
    val mostPopulatedCountry = viewModel.getMostPopulatedCountry()
    val largestCountry = viewModel.getLargestCountry()
    val leastPopulatedCountry = viewModel.getLeastPopulatedCountry()
    val smallestCountry = viewModel.getSmallestCountry()
    val totalCountries = viewModel.getTotalCountries()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Estadísticas Globales") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StatisticCard("País más poblado", mostPopulatedCountry?.name?.common, mostPopulatedCountry?.population, mostPopulatedCountry?.flags?.png)
            StatisticCard("País más grande", largestCountry?.name?.common, largestCountry?.area?.toLong(), largestCountry?.flags?.png)
            StatisticCard("País menos poblado", leastPopulatedCountry?.name?.common, leastPopulatedCountry?.population, leastPopulatedCountry?.flags?.png)
            StatisticCard("País más pequeño", smallestCountry?.name?.common, smallestCountry?.area?.toLong(), smallestCountry?.flags?.png)
            StatisticCard("Total de países", totalCountries.toString(), null, null)
        }
    }
}

@Composable
fun StatisticCard(label: String, value: String?, detail: Any?, flagUrl: String?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            flagUrl?.let {
                AsyncImage(
                    model = it,
                    contentDescription = "Bandera",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(12.dp))
            }

            Column {
                Text(text = label, style = MaterialTheme.typography.titleMedium)
                Text(text = value ?: "No disponible", style = MaterialTheme.typography.bodyLarge)
                detail?.let {
                    Text(
                        text = when (label) {
                            "País más poblado" -> "Población: $detail"
                            "País menos poblado" -> "Población: $detail"
                            "País más grande" -> "Área: $detail km²"
                            "País más pequeño" -> "Área: $detail km²"
                            else -> ""
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
