package com.example.juniorandroiddevelopertask.presentaion.utils

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<Boolean>
}