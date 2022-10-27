package com.example.juniorandroiddevelopertask.data.remote.dto

@kotlinx.serialization.Serializable
data class Owner(
    val avatar_url: String,
    val login: String,
)