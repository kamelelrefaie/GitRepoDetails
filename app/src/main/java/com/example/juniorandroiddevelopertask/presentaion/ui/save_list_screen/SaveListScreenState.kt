package com.example.juniorandroiddevelopertask.presentaion.ui.save_list_screen

import com.example.juniorandroiddevelopertask.domain.model.GitHubRepo

data class SaveListScreenState (
    val repos: List<GitHubRepo> = emptyList(),
    val isLike: Boolean=false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
        )