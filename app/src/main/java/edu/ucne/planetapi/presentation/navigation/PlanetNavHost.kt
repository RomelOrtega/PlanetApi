package edu.ucne.planetapi.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.planetapi.presentation.characters.detail.CharacterDetailScreen
import edu.ucne.planetapi.presentation.characters.detail.CharacterDetailViewModel
import edu.ucne.planetapi.presentation.characters.list.CharacterListScreen
import edu.ucne.planetapi.presentation.details.PlanetDetailScreen
import edu.ucne.planetapi.presentation.list.ListPlanetScreen

enum class NavItem(
    val title: String,
    val icon: ImageVector,
    val startDestination: Screen
) {
    Planets("Planetas", Icons.Default.Public, Screen.List),
    Characters("Personajes", Icons.Default.People, Screen.CharacterList)
}

@Composable
fun PlanetNavHost(navHostController: NavHostController) {
    var selectedItem by remember { mutableStateOf(NavItem.Planets) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            NavItem.entries.forEach { navItem ->
                item(
                    selected = selectedItem == navItem,
                    onClick = {
                        selectedItem = navItem
                        navHostController.navigate(navItem.startDestination) {
                            launchSingleTop = true
                            popUpTo(Screen.List) { inclusive = false }
                        }
                    },
                    icon = { Icon(navItem.icon, contentDescription = navItem.title) },
                    label = { Text(navItem.title) }
                )
            }
        }
    ) {
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

            composable<Screen.CharacterList> {
                CharacterListScreen(
                    onCharacterClick = { id ->
                        navHostController.navigate(Screen.CharacterDetail(id))
                    }
                )
            }

            composable<Screen.CharacterDetail> {
                val viewModel: CharacterDetailViewModel = hiltViewModel()
                val state by viewModel.state.collectAsStateWithLifecycle()
                CharacterDetailScreen(
                    state = state,
                    onBack = { navHostController.navigateUp() }
                )
            }
        }
    }
}