package com.example.juniorandroiddevelopertask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.juniorandroiddevelopertask.utils.Constants.REPO_PAGINATION_PAGE_SIZE

@Dao
interface GitHubRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(entities: List<GitHubRepoEntity>)

    @Query("DELETE FROM GitHubRepoEntity")
    suspend fun deleteAllRepos()

    @Query("DELETE FROM GitHubRepoEntity WHERE repo_name LIKE '%' || :query || '%'")
    suspend fun deleteRepoWithQuery(query: String)

    /**
     * Retrieve repos for a particular page.
     * Ex: page = 2 retrieves recipes from 10-20.
     * Ex: page = 3 retrieves recipes from 20-30
     */
    @Query(
        """
        SELECT * FROM GitHubRepoEntity 
        WHERE repo_name LIKE '%' || :query || '%'
         LIMIT :pageSize OFFSET ((:page ) * :pageSize)
        """
    )
    suspend fun searchRepos(
        query: String,
        page: Int,
        pageSize: Int = REPO_PAGINATION_PAGE_SIZE
    ): List<GitHubRepoEntity>

    @Query("SELECT * FROM GitHubRepoEntity WHERE repo_id = :id")
    suspend fun getRepoById(id: Int): GitHubRepoEntity?
/*
liked repo entity
 */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGitHubSavedRepoEntity(savedRepoEntity: GitHubSavedRepoEntity)

    @Query("DELETE FROM GitHubSavedRepoEntity WHERE repo_id = :id")
    suspend fun deleteSavedRepoEntity(id: Int)

    @Query(
        """
        SELECT * FROM GitHubSavedRepoEntity
         LIMIT :pageSize OFFSET ((:page ) * :pageSize)
        """
    )
    suspend fun getSavedRepos(
        page: Int,
        pageSize: Int = REPO_PAGINATION_PAGE_SIZE
    ): List<GitHubSavedRepoEntity>

    @Query(
        """
        SELECT * FROM GitHubSavedRepoEntity
        WHERE isFav = :isFav
         LIMIT :pageSize OFFSET ((:page ) * :pageSize)
        """
    )
    suspend fun getLovedRepos(
        isFav: Boolean = true,
        page: Int,
        pageSize: Int = REPO_PAGINATION_PAGE_SIZE
    ): List<GitHubSavedRepoEntity>

    @Query("SELECT * FROM GitHubSavedRepoEntity WHERE repo_id = :id")
    suspend fun getSavedRepoById(id: Int): GitHubSavedRepoEntity?

}