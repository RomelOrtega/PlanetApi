package edu.ucne.planetapi.domain.repository

import edu.ucne.planetapi.domain.model.Planet
import edu.ucne.planetapi.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {

    fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Flow<Resource<List<Planet>>>
    fun getPlanetDetail(id: Int): Flow<Resource<Planet>>
}