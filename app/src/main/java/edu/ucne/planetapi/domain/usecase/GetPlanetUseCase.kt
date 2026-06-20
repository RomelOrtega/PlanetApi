package edu.ucne.planetapi.domain.usecase

import edu.ucne.planetapi.domain.repository.PlanetRepository
import javax.inject.Inject

class GetPlanetUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    suspend operator fun invoke() = repository.getPlanets()
}