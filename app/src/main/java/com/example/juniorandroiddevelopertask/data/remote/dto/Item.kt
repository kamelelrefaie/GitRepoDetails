package com.example.juniorandroiddevelopertask.data.remote.dto

@kotlinx.serialization.Serializable
data class Item(
    val open_issues_count: Int,
    val forks_count: Int,
    val stargazers_count: Int,
    val description: String,
    val name: String,
    val owner: Owner,
    val id: Int,

)