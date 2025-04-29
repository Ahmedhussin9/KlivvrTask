package com.klivvrtask.presentaion.composable

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.klivvrtask.data.trie.City

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CityList(cities: List<City>) {
    val context = LocalContext.current
    val grouped = cities.groupBy { it.name.first().uppercaseChar() }

    LazyColumn {
        grouped.forEach { (initial, cityList) ->
            stickyHeader {
                Text(
                    text = initial.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(8.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            items(cityList) { city ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val uri =
                                Uri.parse("geo:${city.coord.lat},${city.coord.lon}?q=${city.name}")
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            intent.setPackage("com.google.android.apps.maps")
                            context.startActivity(intent)
                        }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        "${city.name}, ${city.country}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        "Lat: ${city.coord.lat}, Lon: ${city.coord.lon}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

    }

}