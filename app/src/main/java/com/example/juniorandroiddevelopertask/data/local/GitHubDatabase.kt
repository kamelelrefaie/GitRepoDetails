package com.example.juniorandroiddevelopertask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GithubRepoEntity::class ], version = 1)
abstract class GitHubDatabase: RoomDatabase() {

    abstract fun gitHubDao(): GitHubRepoDao

    companion object{
        const val DATABASE_NAME: String = "github_db"
    }


}