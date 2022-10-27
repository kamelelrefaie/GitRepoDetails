package com.example.juniorandroiddevelopertask.di

import com.example.juniorandroiddevelopertask.data.repository.GitHubRepoImpl
import com.example.juniorandroiddevelopertask.domain.repository.GitHubRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindingRepository(gitHubRepoImpl: GitHubRepoImpl): GitHubRepo
}