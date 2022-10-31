package com.example.juniorandroiddevelopertask.presentaion.ui.search_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.juniorandroiddevelopertask.data.paging.DefaultPaginator
import com.example.juniorandroiddevelopertask.domain.repository.Repository
import com.example.juniorandroiddevelopertask.presentaion.navigation.SEARCH_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
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

    init {
        //get text from Qr scanner
        val saved = savedStateHandle.get<String>(SEARCH_QUERY)
        saved?.let {
            onEvent(SearchScreenEvent.OnSearchQueryChange(it))
        }

    }

    fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.OnSearchQueryChange -> {
                val isNotSameQuery = state.searchQuery != event.query
                if (isNotSameQuery) {
                    resetPagination()
                }
                state = state.copy(searchQuery = event.query)

                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    //added some delay to avoid high typing speed
                    delay(200L)

                    if (state.searchQuery.isEmpty()) state = state.copy(repos = emptyList())
                    else paginator.loadNextItems()
                }
            }
        }
    }


    private fun resetPagination() {
        paginator.reset()
        state = state.copy(repos = emptyList(), page = 0)
    }

}


