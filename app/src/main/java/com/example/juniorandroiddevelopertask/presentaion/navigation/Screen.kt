package com.example.juniorandroiddevelopertask.presentaion.navigation

const val REPO_ID = "repoId"
sealed class Screen(val route: String) {
    object SearchScreen : Screen("searchScreen")
    object RepoDetail : Screen("repoDetail/{${REPO_ID}}"){
        fun repoDetailWithId(id:Int): String {
            return "repoDetail/${id}"
        }
    }
    object FavList : Screen("favList")
}
