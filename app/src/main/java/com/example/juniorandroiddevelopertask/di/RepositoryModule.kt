package com.example.juniorandroiddevelopertask.di

import com.example.juniorandroiddevelopertask.data.repository.RepositoryImpl
import com.example.juniorandroiddevelopertask.domain.repository.Repository
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
    abstract fun bindingRepository(gitHubRepoImpl: RepositoryImpl): Repository
}