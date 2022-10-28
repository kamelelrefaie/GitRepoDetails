package com.example.juniorandroiddevelopertask.presentaion.ui.details_screen

import com.example.juniorandroiddevelopertask.domain.model.Repo

data class DetailsScreenState(
    val repos: Repo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)
