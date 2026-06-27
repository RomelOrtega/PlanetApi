package edu.ucne.planetapi.presentation.characters.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.planetapi.data.remote.Resource
import edu.ucne.planetapi.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterListUiState())
    val state = _state.asStateFlow()

    init {
        loadCharacters()
    }

    fun onEvent(event: CharacterListUiEvent) {
        when (event) {
            is CharacterListUiEvent.UpdateFilters -> {
                _state.update {
                    it.copy(
                        filterName = event.name,
                        filterGender = event.gender,
                        filterRace = event.race
                    )
                }
                applyFilter()
            }
            CharacterListUiEvent.Search -> applyFilter()
        }
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            getCharactersUseCase(
                limit = 100
            ).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }

                    is Resource.Success -> {
                        val data = result.data ?: emptyList()
                        _state.update {
                            it.copy(
                                isLoading = false,
                                allCharacters = data,
                                characters = data
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

    private fun applyFilter() {
        val current = _state.value
        val name = current.filterName.trim()
        val gender = current.filterGender.trim()
        val race = current.filterRace.trim()
        val all = current.allCharacters

        val filtered = all.filter { character ->
            (name.isEmpty() || character.name.contains(name, ignoreCase = true)) &&
                    (gender.isEmpty() || character.gender.contains(gender, ignoreCase = true)) &&
                    (race.isEmpty() || character.race.contains(race, ignoreCase = true))
        }

        _state.update { it.copy(characters = filtered) }
    }
}