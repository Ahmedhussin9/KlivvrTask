package com.klivvrtask.presentaion.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.klivvrtask.R
import com.klivvrtask.presentaion.composable.AnimatedSearchBar
import com.klivvrtask.presentaion.composable.CityListWithTimeline

@Composable
fun SearchScreenSetup(
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    SearchScreenContent(uiState = uiState, onEvent = viewModel::onEvent)
}

@Composable
fun SearchScreenContent(
    uiState: SearchScreenUiState,
    onEvent: (SearchEvents) -> Unit,
) {
    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)) {
        Text(
            "City Search",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, top = 10.dp)
        )
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when {
                uiState.isLoading -> {
                    AnimatedVisibility(visible = true) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }

                uiState.cityResults.isEmpty() -> {
                    AnimatedVisibility(visible = true) {
                      Column (
                          modifier = Modifier.fillMaxSize(),
                          verticalArrangement = Arrangement.Center,
                          horizontalAlignment = Alignment.CenterHorizontally
                      ){
                          Image(
                              painter = painterResource(id = R.drawable.noresults),
                              contentDescription = "No results found",
                              modifier = Modifier.size(70.dp)
                          )
                          Spacer(modifier = Modifier.height(16.dp))
                          Text(
                              text = "No results found",
                              fontSize = 16.sp,
                              fontWeight = FontWeight.Bold,
                              color = Color.Black,
                              modifier = Modifier.align(Alignment.CenterHorizontally)
                          )
                      }
                    }
                }

                else -> {
                    CityListWithTimeline(modifier = Modifier.fillMaxSize().padding(bottom = 80.dp), cities = uiState.cityResults)
                }
            }
        }
        AnimatedSearchBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            query = uiState.searchQuery,
            enabled = !uiState.isLoading,
            onQueryChanged = { onEvent.invoke(SearchEvents.OnSearchQueryChanged(it)) })
    }

}





@Composable
@Preview(showBackground = true)
fun SearchScreenPreview() {
    SearchScreenContent(uiState = SearchScreenUiState(), onEvent = {})

}