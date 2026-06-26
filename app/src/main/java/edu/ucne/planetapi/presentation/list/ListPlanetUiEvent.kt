package edu.ucne.planetapi.presentation.list

sealed interface ListPlanetUiEvent {
    data class UpdateFilters(
        val name: String
    ) : ListPlanetUiEvent

    data object Search : ListPlanetUiEvent
}