package com.example.juniorandroiddevelopertask.domain.paging

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}