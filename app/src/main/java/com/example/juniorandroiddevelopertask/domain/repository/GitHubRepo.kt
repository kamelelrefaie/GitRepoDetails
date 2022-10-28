package com.example.juniorandroiddevelopertask.domain.repository

import com.example.juniorandroiddevelopertask.domain.model.Repo
import com.example.juniorandroiddevelopertask.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GitHubRepo {
     fun searchRepos(
        page: Int,
        query: String
    ): Flow<Resource<List<Repo>>>

     fun getRepoItem(repoId:Int) : Flow<Resource<Repo>>
}