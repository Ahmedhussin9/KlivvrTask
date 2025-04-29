package com.klivvrtask.domain.repository

import com.klivvrtask.data.trie.City

interface CityRepository {
    suspend fun loadCities():Boolean

     fun searchCities(prefix:String):List<City>
}