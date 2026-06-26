package edu.ucne.planetapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.planetapi.presentation.details.PlanetDetailScreen
import edu.ucne.planetapi.presentation.list.ListPlanetScreen

@Composable
fun PlanetNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.List
    ) {
        composable<Screen.List> {
            ListPlanetScreen(
                onPlanetClick = { id ->
                    navHostController.navigate(Screen.Detail(id))
                }
            )
        }

        composable<Screen.Detail> {
            PlanetDetailScreen(
                onBack = { navHostController.navigateUp() }
            )
        }
    }
}