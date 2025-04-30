package com.klivvrtask.presentaion.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klivvrtask.domain.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val cityRepository: CityRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchScreenUiState())
    val uiState: StateFlow<SearchScreenUiState> = _uiState


    init {
        loadCities()
    }
    fun onEvent(events: SearchEvents) {
        when (events) {
            is SearchEvents.OnSearchQueryChanged -> {
                val query = events.query
                val result = if (query.isBlank()) {
                    emptyList()
                } else {
                    cityRepository.searchCities(query)
                }
                _uiState.update { it.copy(searchQuery = events.query, cityResults = result) }
            }

            is SearchEvents.LoadData -> {
                loadCities()
            }
        }
    }

    private fun loadCities() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = withContext(Dispatchers.IO) {
                cityRepository.loadCities()
            }
            _uiState.update {
                it.copy(
                    isLoading = false,
                    error = if (result) null else "Failed to load cities"
                )
            }
        }
    }
}