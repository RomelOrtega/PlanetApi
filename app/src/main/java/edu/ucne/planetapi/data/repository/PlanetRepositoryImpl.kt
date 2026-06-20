package edu.ucne.planetapi.data.repository

import edu.ucne.planetapi.data.remote.DragonBallApi
import edu.ucne.planetapi.domain.model.Planet
import edu.ucne.planetapi.domain.repository.PlanetRepository
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val api: DragonBallApi
) : PlanetRepository {

    override suspend fun getPlanets(): List<Planet> {
        return api.getPlanets().items.map { it.toDomain() }
    }

    override suspend fun getPlanetById(id: Int): Planet {
        return api.getPlanetById(id).toDomain()
    }
}