package com.example.juniorandroiddevelopertask.presentaion.ui.search_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.juniorandroiddevelopertask.data.paging.DefaultPaginator
import com.example.juniorandroiddevelopertask.domain.repository.GitHubRepo
import com.example.juniorandroiddevelopertask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: GitHubRepo
) : ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private var searchJob: Job? = null

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            repository.searchRepos(nextPage, state.searchQuery)
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

    fun onEvent(event: SearchScreenEvent) {
        when (event) {
            SearchScreenEvent.OnFavIconClicked -> TODO()
            SearchScreenEvent.OnRepoClicked -> TODO()

            is SearchScreenEvent.OnSearchQueryChange -> {
                val isNotSameQuery = state.searchQuery != event.query
                if (isNotSameQuery) {
                    paginator.reset()
                    state = state.copy(repos = emptyList(), page = 0)
                }
                Log.e("isnotthesame", "$isNotSameQuery")
                state = state.copy(searchQuery = event.query)

                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(300L)

                    if (state.searchQuery.isEmpty()) state = state.copy(repos = emptyList())
                    else {
                        paginator.loadNextItems()
                    }
                }
            }
        }
    }


}


