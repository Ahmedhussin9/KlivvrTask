package com.klivvrtask.presentaion.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.klivvrtask.data.trie.City
import com.klivvrtask.presentaion.composable.AnimatedSearchBar
import com.klivvrtask.presentaion.composable.CityList

@Composable
fun SearchScreenSetup(
    viewModel: SearchViewModel = hiltViewModel(),
//    navController: NavController
) {
    val uiState = viewModel.uiState
    SearchScreenContent(uiState = uiState, onEvent = viewModel::onEvent, onCityClick = {

    })

}

@Composable
fun SearchScreenContent(
    uiState: SearchScreenUiState,
    onEvent: (SearchEvents) -> Unit,
    onCityClick: (City) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(SearchEvents.LoadData)
    }
    Column(modifier = Modifier.padding(10.dp).fillMaxSize()) {
        AnimatedSearchBar(
            query = uiState.searchQuery,
            onQueryChanged = { onEvent.invoke(SearchEvents.OnSearchQueryChanged(it)) })
        Spacer(modifier = Modifier.height(8.dp))
        when {
            uiState.isLoading -> {
                AnimatedVisibility(visible = true) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }

            uiState.cityResults.isEmpty() -> {
                AnimatedVisibility(visible = true) {
                    Text(
                        text = "No results found",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }

            else -> {
                CityList(cities = uiState.cityResults)
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SearchScreenPreview() {
    SearchScreenContent(uiState = SearchScreenUiState(), onEvent = {}, onCityClick = {})

}