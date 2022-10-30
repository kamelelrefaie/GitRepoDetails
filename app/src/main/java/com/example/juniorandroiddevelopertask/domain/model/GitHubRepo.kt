package com.example.juniorandroiddevelopertask.domain.model

data class GitHubRepo(
    val issuesCount: Int,
    val forksCount: Int,
    val stargazersCount: Int,
    val description: String,
    val repoName: String,
    val avatarUrl: String,
    val isFav:Boolean = false,
    val username: String,
    val repoId : Int
)
