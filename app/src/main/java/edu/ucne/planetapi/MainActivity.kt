package edu.ucne.planetapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.planetapi.presentation.navigation.PlanetNavHost
import edu.ucne.planetapi.ui.theme.PlanetApiTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlanetApiTheme {
                val navController = rememberNavController()
                PlanetNavHost(navHostController = navController)
            }
        }
    }
}