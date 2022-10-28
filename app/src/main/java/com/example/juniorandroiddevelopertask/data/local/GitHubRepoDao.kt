package com.example.juniorandroiddevelopertask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.juniorandroiddevelopertask.utils.Constants.REPO_PAGINATION_PAGE_SIZE

@Dao
interface GitHubRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(entities: List<GithubRepoEntity>)

    @Query("DELETE FROM GithubRepoEntity")
    suspend fun deleteAllRepos()

    @Query("DELETE FROM GithubRepoEntity WHERE repo_name LIKE '%' || :query || '%'")
    suspend fun deleteRepoWithQuery(query:String)

    /**
     * Retrieve repos for a particular page.
     * Ex: page = 2 retrieves recipes from 10-20.
     * Ex: page = 3 retrieves recipes from 20-30
     */
    @Query("""
        SELECT * FROM GithubRepoEntity 
        WHERE repo_name LIKE '%' || :query || '%'
         LIMIT :pageSize OFFSET ((:page - 1) * :pageSize)
        """)

    suspend fun searchRepos(
        query: String,
        page: Int,
        pageSize: Int = REPO_PAGINATION_PAGE_SIZE
    ): List<GithubRepoEntity>

    @Query("SELECT * FROM GithubRepoEntity WHERE repo_id = :id")
    suspend fun getRepoById(id: Int): GithubRepoEntity?


//    @Query("""
//        SELECT * FROM GithubRepoEntity
//        WHERE isFav = 1
//         LIMIT :pageSize OFFSET ((:page - 1) * :pageSize)
//        """)
//
//    suspend fun getFavRepos(
//        isFave: Boolean,
//        page: Int,
//        pageSize: Int = REPO_PAGINATION_PAGE_SIZE
//    ): List<GithubRepoEntity>
}