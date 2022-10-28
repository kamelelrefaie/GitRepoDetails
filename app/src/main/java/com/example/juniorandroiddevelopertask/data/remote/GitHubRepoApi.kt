package com.example.juniorandroiddevelopertask.data.remote

import com.example.juniorandroiddevelopertask.data.remote.dto.GitHubDto
import com.example.juniorandroiddevelopertask.utils.Constants.REPO_PAGINATION_PAGE_SIZE
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubRepoApi {

    @GET("search/repositories")
    suspend fun getReposFromNetwork(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int = REPO_PAGINATION_PAGE_SIZE + 10
    ): GitHubDto

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }
}