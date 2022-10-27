package com.example.juniorandroiddevelopertask.domain.model

data class Repo(
    val issuesCount: Int,
    val forksCount: Int,
    val stargazersCount: Int,
    val description: String,
    val repoName: String,
    val avatarUrl: String,
    val username: String
)
