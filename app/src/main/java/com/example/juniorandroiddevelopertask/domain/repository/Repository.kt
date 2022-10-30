package com.example.juniorandroiddevelopertask.domain.repository

import com.example.juniorandroiddevelopertask.domain.model.GitHubRepo
import com.example.juniorandroiddevelopertask.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun searchRepos(
        page: Int,
        query: String
    ): Flow<Resource<List<GitHubRepo>>>

    fun getRepoItem(repoId: Int): Flow<Resource<GitHubRepo>>
    fun getSavedRepoById(repoId: Int): Flow<Resource<GitHubRepo>>

    suspend fun insertGitHubSavedRepoEntity(repo: GitHubRepo)
    suspend fun deleteSavedRepoEntity(id: Int)
    fun getSavedRepos(page: Int): Flow<Resource<List<GitHubRepo>>>
    fun getLovedRepos(page: Int): Flow<Resource<List<GitHubRepo>>>
}