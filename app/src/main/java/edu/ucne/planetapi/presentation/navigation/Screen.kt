package edu.ucne.planetapi.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object List : Screen()

    @Serializable
    data class Detail(val id: Int) : Screen()
    @Serializable
    data object CharacterList: Screen()
    @Serializable
    data class CharacterDetail(val id: Int): Screen()
}