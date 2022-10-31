package com.example.juniorandroiddevelopertask.presentaion.ui.details_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.juniorandroiddevelopertask.domain.model.GitHubRepo
import com.example.juniorandroiddevelopertask.domain.repository.Repository
import com.example.juniorandroiddevelopertask.presentaion.navigation.REPO_ID
import com.example.juniorandroiddevelopertask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repo: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(DetailsScreenState())
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        val id = savedStateHandle.get<Int>(REPO_ID)
        state = state.copy(isLoading = true)

        id?.let {
            getRepoItem(it)
            getLikedRepo(it)
        }

    }

    private fun getLikedRepo(id: Int) {
        val lovedRepo = repo.getSavedRepoById(id)

        lovedRepo.onEach {
            state = when (it) {
                is Resource.Error -> state.copy(isSaved = false, isLiked = false, isLoading = false)
                is Resource.Loading -> state.copy(isLoading = true)
                is Resource.Success -> state.copy(isSaved = true, isLiked = it.data!!.isFav, isLoading = false)
            }
        }.launchIn(viewModelScope)
    }


    fun onEvent(event: DetailsScreenEvent) {
        when (event) {
            DetailsScreenEvent.OnSavedBtnClicked -> insertGitHubSavedRepoEntity(toastMsg = "Saved Successfully")
            DetailsScreenEvent.OnUndoBtnClicked -> deleteSavedRepoEntity()
            DetailsScreenEvent.OnLovedBtnClicked -> insertGitHubSavedRepoEntity(
                true,
                "added to favourites"
            )
            DetailsScreenEvent.OnUnLovedBtnClicked -> insertGitHubSavedRepoEntity(toastMsg = "Removed From Favourites")
        }
    }

    private fun deleteSavedRepoEntity() {
        viewModelScope.launch {
            repo.deleteSavedRepoEntity(state.repos!!.repoId)
            _eventFlow.emit(UiEvent.ShowSnackBar("Repository Deleted SuccessFully"))
            getLikedRepo(state.repos!!.repoId)
        }

    }

    private fun insertGitHubSavedRepoEntity(isFav: Boolean = false, toastMsg: String) {
        viewModelScope.launch {
            repo.insertGitHubSavedRepoEntity(state.repos!!.copy(isFav = isFav))
            _eventFlow.emit(UiEvent.ShowSnackBar(toastMsg))
            getLikedRepo(state.repos!!.repoId)
        }

    }

    private fun getRepoItem(repoId: Int) {
        val repoItemFromPagination = repo.getRepoItem(repoId)
        getGitHubRepo(repoItemFromPagination)

        state.repos.let {
            val repoItem = repo.getSavedRepoById(repoId)
            getGitHubRepo(repoItem)
        }

    }

    private fun getGitHubRepo(gitHubRepo: Flow<Resource<GitHubRepo>>) {
        viewModelScope.launch {
            gitHubRepo.onEach {
                delay(500L)
                state = when (it) {
                    is Resource.Error -> {
                        state.copy(isLoading = false, error = it.message)
                    }
                    is Resource.Loading -> {
                        state.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        state.copy(repos = it.data, isLoading = false)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
    }

}