package com.weatherapp.feature.detail.di

import com.weatherapp.feature.detail.domain.DetailRepository
import com.weatherapp.feature.detail.data.DetailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailModule {

    @Binds
    @Singleton
    abstract fun bindDetailRepository(
        impl: DetailRepositoryImpl
    ): DetailRepository
}