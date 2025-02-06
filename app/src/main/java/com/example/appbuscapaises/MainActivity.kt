package com.example.appbuscapaises

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appbuscapaises.ui.theme.AppBuscaPaisesTheme
import com.example.appbuscapaises.view.CountryDetailScreen
import com.example.appbuscapaises.view.CountryListScreen
import com.example.appbuscapaises.view.CountrySearchScreen
import com.example.appbuscapaises.view.StatisticsScreen
import com.example.appbuscapaises.viewModel.CountryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppBuscaPaisesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // Esto devuelve un NavHostController
    val navController = rememberNavController()
    AppNavigator(navController)
}


@Composable
fun AppNavigator(navController: NavHostController) {
    val viewModel: CountryViewModel = viewModel()

    NavHost(navController = navController, startDestination = "search") {
        composable("search") { CountrySearchScreen(navController, viewModel) }
        composable("home/{query}") { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""
            CountryListScreen(navController, query, viewModel)
        }
        composable("details/{countryName}") { backStackEntry ->
            val countryName = backStackEntry.arguments?.getString("countryName") ?: ""
            CountryDetailScreen(countryName, viewModel, navController)
        }
        composable("statistics") {
            StatisticsScreen(navController, viewModel)
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppBuscaPaisesTheme {
        Greeting("Android")
    }
}