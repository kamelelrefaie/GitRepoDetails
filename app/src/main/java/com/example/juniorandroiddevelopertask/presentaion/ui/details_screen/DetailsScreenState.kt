package com.example.juniorandroiddevelopertask.presentaion.ui.details_screen

import com.example.juniorandroiddevelopertask.domain.model.GitHubRepo

data class DetailsScreenState(
    val repos: GitHubRepo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSaved: Boolean = false,
    val isLiked: Boolean=false
)
