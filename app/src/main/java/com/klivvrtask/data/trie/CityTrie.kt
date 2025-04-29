package com.klivvrtask.data.trie

class CityTrie {
    private val root = TrieNode()

    fun insert(city: City) {
        var node = root
        val name = city.name.lowercase()
        for (char in name) {
            node = node.children.getOrPut(char) { TrieNode() }
        }
        node.cities.add(city)
    }

    fun search(prefix: String): List<City> {
        var node = root
        for (char in prefix.lowercase()) {
            node = node.children[char] ?: return emptyList()
        }
        return collectAllCities(node)
    }

    private fun collectAllCities(node: TrieNode): List<City> {
        val result = mutableListOf<City>()
        result.addAll(node.cities)
        for (child in node.children.values) {
            result.addAll(collectAllCities(child))
        }
        return result
    }
}