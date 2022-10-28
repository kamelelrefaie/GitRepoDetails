package com.example.juniorandroiddevelopertask.presentaion.navigation

import android.util.Log
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.juniorandroiddevelopertask.presentaion.ui.details_screen.DetailScreen
import com.example.juniorandroiddevelopertask.presentaion.ui.search_screen.SearchScreen

@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.SearchScreen.route ){

        composable(route = Screen.SearchScreen.route){
            SearchScreen(navHostController)
        }

        composable(route = Screen.RepoDetail.route, arguments = listOf(navArgument(name = REPO_ID){
            type = NavType.IntType
        })){
          Log.e("idname",it.arguments?.getInt(REPO_ID).toString())
         DetailScreen(navHostController = navHostController)
        }

        composable(route = Screen.FavList.route){

        }
    }


}

