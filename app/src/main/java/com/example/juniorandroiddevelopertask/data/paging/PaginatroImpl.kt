package com.example.juniorandroiddevelopertask.data.paging

import android.util.Log
import com.example.juniorandroiddevelopertask.domain.paging.Paginator
import com.example.juniorandroiddevelopertask.utils.Resource
import kotlinx.coroutines.flow.Flow

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Flow<Resource<List<Item>>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (String?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
): Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if(isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false

        result.collect{
            when(it){
                is Resource.Error -> {
                    onError(it.message)
                    onLoadUpdated(false)
                }
                is Resource.Loading ->  onLoadUpdated(true)

                is Resource.Success -> {
                    currentKey = getNextKey(it.data!!)
                    onSuccess(it.data, currentKey)
                    onLoadUpdated(false)
                }
            }
        }

    }

    override fun reset() {
        currentKey = initialKey
    }
}