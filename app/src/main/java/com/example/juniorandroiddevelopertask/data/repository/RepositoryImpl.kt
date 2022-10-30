package com.example.juniorandroiddevelopertask.data.repository

import android.util.Log
import com.example.juniorandroiddevelopertask.data.local.GitHubDatabase
import com.example.juniorandroiddevelopertask.data.mapper.toGitHubRepo
import com.example.juniorandroiddevelopertask.data.mapper.toGithubEntity
import com.example.juniorandroiddevelopertask.data.mapper.toGithubRepoEntity
import com.example.juniorandroiddevelopertask.data.mapper.toRep
import com.example.juniorandroiddevelopertask.data.remote.GitHubRepoApi
import com.example.juniorandroiddevelopertask.domain.model.GitHubRepo
import com.example.juniorandroiddevelopertask.domain.repository.Repository
import com.example.juniorandroiddevelopertask.utils.Constants.NAME_QUALIFIER
import com.example.juniorandroiddevelopertask.utils.Constants.REPO_PAGINATION_PAGE_SIZE
import com.example.juniorandroiddevelopertask.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    db: GitHubDatabase,
    private val api: GitHubRepoApi
) : Repository {
    private val dao = db.gitHubDao()

    override fun searchRepos(page: Int, query: String): Flow<Resource<List<GitHubRepo>>> = flow {
        try {
            emit(Resource.Loading())

            try {
                val getCached =  dao.searchRepos(
                    query = query,
                    pageSize = REPO_PAGINATION_PAGE_SIZE,
                    page = page
                )

               if ( getCached.isEmpty()){
                   val repos =
                       api.getReposFromNetwork(query = "$query $NAME_QUALIFIER", page = page).items
                   Log.e("page", "page $page")
                   val isFirstPage = page == 0
                   if (isFirstPage) dao.deleteRepoWithQuery(query)

                   // insert into cache
                   dao.insertRepos(repos.map {
                       it.toGithubEntity()
                   })
               }


            } catch (e: Exception) {
                // There was a network issue
                e.printStackTrace()
            }

            // get Cached
            val cacheResult = dao.searchRepos(
                query = query,
                pageSize = REPO_PAGINATION_PAGE_SIZE,
                page = page
            ).map {
                it.toRep()
            }

            Log.e("page", "$cacheResult")

            //emit from caching
            emit(Resource.Success(cacheResult))

        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }

    }

    override fun getRepoItem(repoId: Int): Flow<Resource<GitHubRepo>> = flow {
        try {
            emit(Resource.Loading())
            // get Cached
            val cacheResult = dao.getRepoById(repoId)

            cacheResult?.let {
                emit(Resource.Success(it.toRep()))
            } ?: emit(Resource.Error("not found"))

        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }

    override fun getSavedRepoById(repoId: Int): Flow<Resource<GitHubRepo>> = flow {
        try {
            emit(Resource.Loading())
            // get Cached
            val cacheResult = dao.getSavedRepoById(repoId)

            cacheResult?.let {
                emit(Resource.Success(it.toGitHubRepo()))
            } ?: emit(Resource.Error("not found"))

        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }

    override suspend fun insertGitHubSavedRepoEntity(repo: GitHubRepo) {
        try {
            dao.insertGitHubSavedRepoEntity(repo.toGithubRepoEntity())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun deleteSavedRepoEntity(id: Int) {
        try {
            dao.deleteSavedRepoEntity(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getSavedRepos(page: Int): Flow<Resource<List<GitHubRepo>>> = flow {
        try {
            emit(Resource.Loading())
            val cacheResult = dao.getSavedRepos(page, REPO_PAGINATION_PAGE_SIZE).map {
                it.toGitHubRepo()
            }

            emit(Resource.Success(cacheResult))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }

    override fun getLovedRepos(page: Int): Flow<Resource<List<GitHubRepo>>> = flow {
        try {
            emit(Resource.Loading())
            val cacheResult =
                dao.getLovedRepos(page = page, pageSize = REPO_PAGINATION_PAGE_SIZE).map {
                    it.toGitHubRepo()
                }

            emit(Resource.Success(cacheResult))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }


}

