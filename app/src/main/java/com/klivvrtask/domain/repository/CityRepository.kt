package com.klivvrtask.domain.repository

import com.klivvrtask.data.trie.City

interface CityRepository {
    suspend fun loadCities():Boolean

    suspend fun searchCities(prefix:String):List<City>
}