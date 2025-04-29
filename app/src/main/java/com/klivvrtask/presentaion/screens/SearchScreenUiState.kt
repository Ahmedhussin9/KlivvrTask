package com.klivvrtask.presentaion.screens

import com.klivvrtask.data.trie.City

data class SearchScreenUiState(
    val searchQuery: String = "",
    val cityResults: List<City> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
