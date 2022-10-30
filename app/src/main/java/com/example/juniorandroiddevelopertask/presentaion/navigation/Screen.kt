package com.example.juniorandroiddevelopertask.presentaion.navigation

const val REPO_ID = "repoId"
const val SEARCH_QUERY = "searchQuery"

sealed class Screen(val route: String) {
    object SearchScreen : Screen("searchScreen/{${SEARCH_QUERY}}") {
        fun searchScreenWithSearchQuery(searchQuery: String): String {
            return "searchScreen/${searchQuery}"
        }
    }

    object RepoDetail : Screen("repoDetail/{${REPO_ID}}") {
        fun repoDetailWithId(id: Int): String {
            return "repoDetail/${id}"
        }
    }

    object SaveListScreen : Screen("favList")
    object CameraScreen : Screen("cameraScreen")

}
