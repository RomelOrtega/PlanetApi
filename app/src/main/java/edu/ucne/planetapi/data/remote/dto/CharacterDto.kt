package edu.ucne.planetapi.data.remote.dto

import edu.ucne.planetapi.domain.model.Character

data class CharacterResponseDto(
    val items: List<CharacterDto>
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val ki: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val maxKi: String,
) {
    fun toDomain() = Character(
        id, name, ki, race, gender, description, image, maxKi,
    )
}