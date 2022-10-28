package com.example.juniorandroiddevelopertask.presentaion.ui.details_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.juniorandroiddevelopertask.domain.repository.GitHubRepo
import com.example.juniorandroiddevelopertask.presentaion.navigation.REPO_ID
import com.example.juniorandroiddevelopertask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repo: GitHubRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(DetailsScreenState())
        private set

    init {
        val id = savedStateHandle.get<Int>(REPO_ID)
        state = state.copy(isLoading = true)
        id?.let {
           getRepoItem(it)
        }
    }

    private fun getRepoItem(repoId: Int) {
        val repoItem = repo.getRepoItem(repoId)
        repoItem.onEach {
            delay(500L)
            state = when (it) {
                is Resource.Error -> {
                    state.copy(isLoading = false, error = it.message)
                }
                is Resource.Loading -> {
                    state.copy(isLoading = true)
                }
                is Resource.Success -> {
                    state.copy(repos = it.data,isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }


}