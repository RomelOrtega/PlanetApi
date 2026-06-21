package edu.ucne.planetapi.presentation.details

import edu.ucne.planetapi.domain.model.Planet

data class PlanetDetailUiState(
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)