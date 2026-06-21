package edu.ucne.planetapi.presentation.list

import edu.ucne.planetapi.domain.model.Planet

data class ListPlanetUiState(
    val isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val filtro: String = "",
    val error: String? = null
)