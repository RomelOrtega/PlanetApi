package edu.ucne.planetapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.planetapi.presentation.list.ListPlanetScreen
import edu.ucne.planetapi.presentation.details.PlanetDetailScreen

@Composable
fun PlanetNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.PlanetList
    ) {
        composable<Screen.PlanetList> {
            ListPlanetScreen(
                goToPlanet = { id ->
                    navHostController.navigate(Screen.PlanetDetail(id))
                }
            )
        }
        composable<Screen.PlanetDetail> {
            val args = it.toRoute<Screen.PlanetDetail>()
            PlanetDetailScreen(
                planetId = args.planetId,
                onNavigateBack = { navHostController.navigateUp() }
            )
        }
    }
}