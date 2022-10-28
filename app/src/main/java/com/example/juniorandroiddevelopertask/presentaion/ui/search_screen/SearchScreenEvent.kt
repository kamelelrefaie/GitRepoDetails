package com.example.juniorandroiddevelopertask.presentaion.ui.search_screen

sealed class SearchScreenEvent {
    data class OnSearchQueryChange(val query: String): SearchScreenEvent()
}