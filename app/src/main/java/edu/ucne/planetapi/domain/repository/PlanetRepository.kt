package edu.ucne.planetapi.domain.repository

import edu.ucne.planetapi.domain.model.Planet

interface PlanetRepository {
    suspend fun getPlanets(): List<Planet>
    suspend fun getPlanetById(id: Int): Planet
}