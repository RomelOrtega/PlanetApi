package edu.ucne.planetapi.presentation.characters.detail

import edu.ucne.planetapi.domain.model.Character

data class CharacterDetailUiState (
    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: String? = null,
)