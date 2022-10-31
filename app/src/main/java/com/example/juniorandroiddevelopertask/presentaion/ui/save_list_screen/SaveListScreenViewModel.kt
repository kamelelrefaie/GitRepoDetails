package com.example.juniorandroiddevelopertask.presentaion.ui.save_list_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.juniorandroiddevelopertask.data.paging.DefaultPaginator
import com.example.juniorandroiddevelopertask.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveListScreenViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    var state by mutableStateOf(SaveListScreenState())
        private set

    private var getJob: Job? = null

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            if (!state.isLike) {
                repository.getSavedRepos(nextPage)

            } else {
                repository.getLovedRepos(nextPage)
            }
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it)
        },
        onSuccess = { repos, newKey ->
            state = state.copy(
                repos = state.repos + repos,
                page = newKey,
                endReached = repos.isEmpty()
            )
        }
    )

    init {
        loadMore()
    }

    fun onEvent(event: SaveListScreenEvent) {
        when (event) {
            SaveListScreenEvent.LikedRepos -> getRepos(true)
            SaveListScreenEvent.SavedRepos -> getRepos(false)
            SaveListScreenEvent.LoadMore -> loadMore()
        }
    }

    private fun getRepos(isLike:Boolean ) {
        paginator.reset()
        state = state.copy(page = 0, repos = emptyList(), isLike = isLike)
        loadMore()
    }

    private fun loadMore() {
        getJob?.cancel()
        getJob = viewModelScope.launch {
            delay(300L)
            paginator.loadNextItems()
        }
    }


}