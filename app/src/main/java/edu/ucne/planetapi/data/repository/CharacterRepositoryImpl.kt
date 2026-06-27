package edu.ucne.planetapi.data.repository

import edu.ucne.planetapi.data.remote.Resource
import edu.ucne.planetapi.data.remote.CharacterRemoteDataSource
import edu.ucne.planetapi.domain.repository.CharacterRepository
import edu.ucne.planetapi.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource
) : CharacterRepository {
    override fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?
    ): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading())

        val response = remoteDataSource.getCharacters(page, limit, name, gender, race)
        response.onSuccess { characters ->
            emit(Resource.Success(characters.items.map { it.toDomain() }))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido."))
        }
    }

    override fun getCharacterDetail(id: Int): Flow<Resource<Character>> = flow {
        emit(Resource.Loading())

        val response = remoteDataSource.getCharacterDetail(id)
        response.onSuccess { character ->
            emit(Resource.Success(character.toDomain()))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido."))
        }
    }
}