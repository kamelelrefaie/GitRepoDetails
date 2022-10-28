package com.example.juniorandroiddevelopertask.data.remote.dto

data class GitHubDto(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)