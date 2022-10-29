package com.example.juniorandroiddevelopertask.presentaion.ui.details_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.juniorandroiddevelopertask.domain.model.GitHubRepo
import com.example.juniorandroiddevelopertask.presentaion.ui.details_screen.components.RepoDetailContent
import com.example.juniorandroiddevelopertask.presentaion.ui.details_screen.components.RepoDetailDescription
import com.example.juniorandroiddevelopertask.presentaion.ui.details_screen.components.RepoUsernameAvatar
import com.example.juniorandroiddevelopertask.presentaion.ui.details_screen.components.TopBarWithBack


@Composable
fun DetailScreen(
    navHostController: NavHostController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state

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
                    onFavClicked = {}
                ,it.isFav)
                Column {
                    Content(it)
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

