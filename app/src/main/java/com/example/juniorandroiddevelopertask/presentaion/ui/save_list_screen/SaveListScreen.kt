package com.example.juniorandroiddevelopertask.presentaion.ui.save_list_screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ForkRight
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
import com.example.juniorandroiddevelopertask.presentaion.ui.search_screen.SearchScreenEvent
import com.example.juniorandroiddevelopertask.presentaion.ui.search_screen.components.RepoItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveListScreen(navHostController: NavHostController) {
    val viewModel: SaveListScreenViewModel = hiltViewModel()
    val state = viewModel.state

    Column() {
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            AssistChip(
                onClick = { viewModel.onEvent(SaveListScreenEvent.SavedRepos) },
                shape = RoundedCornerShape(8.dp), label = { Text(text = "Saved Repos") },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Save,
                        contentDescription = "Localized description",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            )

            AssistChip(
                onClick = { viewModel.onEvent(SaveListScreenEvent.LikedRepos) },
                shape = RoundedCornerShape(8.dp), label = { Text(text = "Liked Repos") },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Localized description",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
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