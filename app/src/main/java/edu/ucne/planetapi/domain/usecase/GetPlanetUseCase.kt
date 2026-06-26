package edu.ucne.planetapi.domain.usecase

import edu.ucne.planetapi.domain.repository.PlanetRepository
import javax.inject.Inject

class GetPlanetUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    operator fun invoke(
        page: Int = 1,
        limit: Int = 10,
        name: String? = null
    ) = repository.getPlanets(page, limit, name)
}