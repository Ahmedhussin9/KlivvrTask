package com.klivvrtask.data.trie

class TrieNode {
    val children:MutableMap<Char,TrieNode> = mutableMapOf()
    val cities :MutableList<City> = mutableListOf()
}