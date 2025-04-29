package com.klivvrtask.presentaion.screens

sealed class SearchEvents {
    data class OnSearchQueryChanged(val query: String) : SearchEvents()
    object LoadData:SearchEvents()
}