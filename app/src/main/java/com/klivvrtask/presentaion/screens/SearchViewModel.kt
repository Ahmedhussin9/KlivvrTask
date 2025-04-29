package com.klivvrtask.presentaion.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klivvrtask.data.trie.City
import com.klivvrtask.domain.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val cityRepository: CityRepository
) : ViewModel() {
    var uiState by mutableStateOf(SearchScreenUiState())
        private set

    fun onEvent(events: SearchEvents) {
        when (events) {
            is SearchEvents.OnSearchQueryChanged -> {
                val result = cityRepository.searchCities(events.query)
                uiState = uiState.copy(
                    searchQuery = events.query,
                    cityResults = result
                )
            }

            is SearchEvents.LoadData -> {
                loadCities()
            }
        }
    }

    fun loadCities() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                uiState = uiState.copy(
                    isLoading = true
                )
            }
            withContext(Dispatchers.Default){
                val result = cityRepository.loadCities()
                uiState = uiState.copy(
                    isLoading = false,
                    error = if (result) null else "Failed to load cities",

                )

            }
        }
    }
}