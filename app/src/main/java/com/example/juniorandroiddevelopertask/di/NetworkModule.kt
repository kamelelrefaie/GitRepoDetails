package com.example.juniorandroiddevelopertask.di

import com.example.juniorandroiddevelopertask.data.remote.GitHubRepoApi
import com.example.juniorandroiddevelopertask.data.remote.GitHubRepoApi.Companion.BASE_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideGithubApi(): GitHubRepoApi {


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create()
    }


}