package edu.ucne.planetapi.data.remote

import edu.ucne.planetapi.data.remote.dto.PlanetDto
import edu.ucne.planetapi.data.remote.dto.PlanetsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DragonBallApi {

    @GET("planets")
    suspend fun getPlanets(
        @Query("limit") limit: Int = 20
    ): PlanetsResponse

    @GET("planets/{id}")
    suspend fun getPlanetById(
        @Path("id") id: Int
    ): PlanetDto
}