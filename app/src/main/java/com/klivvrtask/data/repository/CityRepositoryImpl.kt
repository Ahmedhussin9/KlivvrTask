package com.klivvrtask.data.repository

import android.content.Context
import android.util.Log
import com.klivvrtask.data.trie.City
import com.klivvrtask.data.trie.CityTrie
import com.klivvrtask.domain.repository.CityRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.decodeToSequence
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CityRepository {
    private val cityTrie = CityTrie()

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun loadCities(): Boolean {
        return try {


            val inputStream = context.assets.open("cities.json")
            val cities = Json.decodeFromStream<List<City>>(inputStream)


            cities.forEach { cityTrie.insert(it) }

            true
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("track", e.message.toString(), )
            false
        }
    }

    override fun searchCities(prefix: String): List<City> {
        return cityTrie.search(prefix)
    }
}