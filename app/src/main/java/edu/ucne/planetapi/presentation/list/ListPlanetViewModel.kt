package edu.ucne.planetapi.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.planetapi.data.remote.Resource
import edu.ucne.planetapi.domain.usecase.GetPlanetUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ListPlanetViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListPlanetUiState())
    val state = _state.asStateFlow()

    init {
        loadPlanets()
    }

    fun onEvent(event: ListPlanetUiEvent) {
        when (event) {
            is ListPlanetUiEvent.UpdateFilters -> {
                val name = event.name.trim()
                val all = _state.value.allPlanets
                val filtered = if (name.isEmpty()) {
                    all
                } else {
                    all.filter { it.name.contains(name, ignoreCase = true) }
                }
                _state.update {
                    it.copy(
                        filterName = event.name,
                        planets = filtered
                    )
                }
            }
            ListPlanetUiEvent.Search -> {
                val name = _state.value.filterName.trim()
                val all = _state.value.allPlanets
                val filtered = if (name.isEmpty()) {
                    all
                } else {
                    all.filter { it.name.contains(name, ignoreCase = true) }
                }
                _state.update { it.copy(planets = filtered) }
            }
        }
    }

    private fun loadPlanets() {
        viewModelScope.launch {
            getPlanetsUseCase(
                limit = 100
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                allPlanets = result.data ?: emptyList(),
                                planets = result.data ?: emptyList()
                            )
                        }
                    }

                    is Resource.Error ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                }
            }
        }
    }
}