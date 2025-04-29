package com.klivvrtask.data.trie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class City(
    @SerialName("_id")
    val id: Int,
    val name: String,
    val country: String,
    val coord: Coord
)

@Serializable
data class Coord(
    val lon: Double,
    val lat: Double
)