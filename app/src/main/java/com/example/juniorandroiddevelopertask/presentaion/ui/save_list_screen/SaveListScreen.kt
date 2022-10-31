package com.example.juniorandroiddevelopertask.presentaion.ui.save_list_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.juniorandroiddevelopertask.presentaion.ui.save_list_screen.components.ChipMaker
import com.example.juniorandroiddevelopertask.presentaion.ui.search_screen.components.RepoItem

@Composable
fun SaveListScreen(navHostController: NavHostController) {
    val viewModel: SaveListScreenViewModel = hiltViewModel()
    val state = viewModel.state

    Column {
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            ChipMaker(
                onClick = { viewModel.onEvent(SaveListScreenEvent.SavedRepos) },
                isLike = !state.isLike,
                label = "Saved Repos",
                imageVector = Icons.Filled.Save
            )
            ChipMaker(
                onClick = { viewModel.onEvent(SaveListScreenEvent.LikedRepos) },
                isLike = state.isLike,
                label = "Liked Repos",
                imageVector = Icons.Filled.Favorite
            )
        }

        AnimatedVisibility(visible = state.repos.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Saved Repos", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            items(state.repos.size) {

                if (it >= state.repos.size - 1 && !state.endReached && !state.isLoading) {
                    viewModel.onEvent(SaveListScreenEvent.LoadMore)
                }

                val item = state.repos[it]
                RepoItem(item, navHostController)

            }
            item {
                AnimatedVisibility(visible = state.isLoading) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

}