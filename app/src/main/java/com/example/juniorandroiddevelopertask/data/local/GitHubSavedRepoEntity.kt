package com.example.juniorandroiddevelopertask.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GitHubSavedRepoEntity(
    val open_issues_count: Int,
    val forks_count: Int,
    val stargazers_count: Int,
    val description: String,
    val repo_name: String,
    val avatar_url: String,
    val login: String,
    val isFav:Boolean=false,
    // get from api
    @PrimaryKey(autoGenerate = false)
    val repo_id: Int
)
