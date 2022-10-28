package com.example.juniorandroiddevelopertask.presentaion.ui.search_screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.juniorandroiddevelopertask.presentaion.ui.search_screen.components.RepoItem
import com.example.juniorandroiddevelopertask.presentaion.ui.search_screen.components.SearchInputComponent
import com.example.juniorandroiddevelopertask.presentaion.ui.search_screen.components.SearchTopBar

@Composable
fun SearchScreen(navHostController: NavHostController = rememberNavController()) {
    val viewModel: SearchViewModel = hiltViewModel()
    val state = viewModel.state
    Column(modifier = Modifier.fillMaxSize()) {
        SearchTopBar(navHostController)
        SearchInputComponent(searchValue = state.searchQuery, onSearchValueChange = {
            viewModel.onEvent(SearchScreenEvent.OnSearchQueryChange(it))
        }, onQrClick = {})

        AnimatedVisibility(visible = state.searchQuery.isEmpty()) {
           Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
               Text(text = "Search area here" , fontWeight = FontWeight.Bold, fontSize = 20.sp)
           }
        }
        LazyColumn(
            Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            items(state.repos.size) {
                Log.e(
                    "",
                    "${state.repos.size} and ${state.endReached} and ${state.isLoading} and it ${it}"
                )
                if (it >= state.repos.size - 1 && !state.endReached && !state.isLoading) {
                    viewModel.onEvent(SearchScreenEvent.OnSearchQueryChange(state.searchQuery))
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