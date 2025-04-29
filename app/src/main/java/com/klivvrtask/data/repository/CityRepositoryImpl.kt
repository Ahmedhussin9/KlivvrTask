package com.klivvrtask.data.repository

import android.content.Context
import com.klivvrtask.data.trie.City
import com.klivvrtask.data.trie.CityTrie
import com.klivvrtask.domain.repository.CityRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeToSequence
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CityRepository {
    private val cityTrie = CityTrie()

    override suspend fun loadCities(): Boolean {
        return try {
            val json = context.assets.open("cities.json")
                .bufferedReader()
                .use { it.readText() }

            val cities = Json.decodeFromString<List<City>>(json)
            cities.forEach { cityTrie.insert(it) }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun searchCities(prefix: String): List<City> {
        return cityTrie.search(prefix)
    }
}