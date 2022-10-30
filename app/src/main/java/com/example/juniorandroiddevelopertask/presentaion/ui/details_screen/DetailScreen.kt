package com.example.juniorandroiddevelopertask.presentaion.ui.details_screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.juniorandroiddevelopertask.domain.model.GitHubRepo
import com.example.juniorandroiddevelopertask.presentaion.theme.Grey
import com.example.juniorandroiddevelopertask.presentaion.ui.details_screen.components.RepoDetailContent
import com.example.juniorandroiddevelopertask.presentaion.ui.details_screen.components.RepoDetailDescription
import com.example.juniorandroiddevelopertask.presentaion.ui.details_screen.components.RepoUsernameAvatar
import com.example.juniorandroiddevelopertask.presentaion.ui.details_screen.components.TopBarWithBack
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val event = viewModel.eventFlow
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        event.collect {
            when (it) {
                is DetailsViewModel.UiEvent.ShowSnackBar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            it.message,
                            duration = SnackbarDuration.Short
                        )
                    }
                }

            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Grey
    ) {
        AnimatedVisibility(visible = state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        state.repos?.let {
            Box(Modifier.verticalScroll(rememberScrollState())) {
                Column {
                    TopBarWithBack(
                        title = it.username,
                        onBackClick = {
                            navHostController.navigateUp()
                        },
                        onSaveClicked = {
                            if (!viewModel.state.isSaved) viewModel.onEvent(DetailsScreenEvent.OnSavedBtnClicked)
                            else viewModel.onEvent(DetailsScreenEvent.OnUndoBtnClicked)
                        }, isLiked = state.isLiked, isSaved = state.isSaved, onLovedClicked = {
                            if (!viewModel.state.isLiked) viewModel.onEvent(DetailsScreenEvent.OnLovedBtnClicked)
                            else viewModel.onEvent(DetailsScreenEvent.OnUnLovedBtnClicked)
                        })
                    Column {
                        Content(it)
                    }
                }
            }
        }
    }


}

@Composable
fun Content(repo: GitHubRepo) {
    Column() {
        Spacer(modifier = Modifier.height(20.dp))
        RepoUsernameAvatar(repo.avatarUrl)
        Spacer(modifier = Modifier.height(20.dp))
        RepoDetailContent(repo.repoName)
        RepoDetailDescription(repo)
    }
}

