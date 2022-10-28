package com.example.juniorandroiddevelopertask.di

import android.app.Application
import androidx.room.Room
import com.example.juniorandroiddevelopertask.data.local.GitHubDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): GitHubDatabase {
        return Room
            .databaseBuilder(app, GitHubDatabase::class.java, GitHubDatabase.DATABASE_NAME)
            .build()
    }

}