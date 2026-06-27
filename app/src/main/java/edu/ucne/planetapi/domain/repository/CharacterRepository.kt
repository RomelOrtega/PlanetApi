package edu.ucne.planetapi.domain.repository

import edu.ucne.planetapi.domain.model.Character
import edu.ucne.planetapi.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?,
    ): Flow<Resource<List<Character>>>

    fun getCharacterDetail(id: Int): Flow<Resource<Character>>
}