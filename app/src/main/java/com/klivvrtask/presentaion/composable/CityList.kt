package com.klivvrtask.presentaion.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.klivvrtask.data.trie.City
import com.klivvrtask.data.trie.Coord

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CityListWithTimeline(modifier: Modifier = Modifier, cities: List<City>) {
    val grouped = remember(cities) {
        cities.groupBy { it.name.first().uppercaseChar() }
            .toSortedMap()
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White) // light gray background
    ) {

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${cities.size} cities",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 8.dp)
                )
            }

        }

        grouped.forEach { (initial, cityList) ->
            stickyHeader {
                Column (
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(start = 12.dp,top = 16.dp, bottom = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .border(2.dp, Color.Gray, CircleShape)
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = initial.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            items(cityList) { city ->
                CityCard(city = city)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCityListWithTimeline() {
    val mockCities = listOf(
        City(
            id = 1,
            name = "Aabenraa",
            country = "DK",
            coord = Coord(9.41741, 55.044338)
        ),
        City(
            id = 2,
            name = "Aalborg",
            country = "DK",
            coord = Coord(9.9177, 57.0488)
        ),
        City(
            id = 3,
            name = "Zaamslag",
            country = "NL",
            coord = Coord(3.9125, 51.3125)
        )
    )
    CityListWithTimeline(cities = mockCities)
}