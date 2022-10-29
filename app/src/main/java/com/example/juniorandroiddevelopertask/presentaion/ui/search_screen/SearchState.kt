package com.example.juniorandroiddevelopertask.presentaion.ui.search_screen

import com.example.juniorandroiddevelopertask.domain.model.GitHubRepo

data class SearchState(
    val repos: List<GitHubRepo> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)
