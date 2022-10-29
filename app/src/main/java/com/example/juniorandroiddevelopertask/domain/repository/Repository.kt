package com.example.juniorandroiddevelopertask.domain.repository

import com.example.juniorandroiddevelopertask.domain.model.GitHubRepo
import com.example.juniorandroiddevelopertask.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
     fun searchRepos(
        page: Int,
        query: String
    ): Flow<Resource<List<GitHubRepo>>>

     fun getRepoItem(repoId:Int) : Flow<Resource<GitHubRepo>>
}