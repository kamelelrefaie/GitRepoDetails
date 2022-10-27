package com.example.juniorandroiddevelopertask.di

import com.example.juniorandroiddevelopertask.presentaion.utils.ConnectivityObserver
import com.example.juniorandroiddevelopertask.presentaion.utils.ConnectivityObserverImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectivityModule {

    @Singleton
    @Binds
    abstract fun bindingConnectivityObserver(connectivityObserverImpl: ConnectivityObserverImpl): ConnectivityObserver
}