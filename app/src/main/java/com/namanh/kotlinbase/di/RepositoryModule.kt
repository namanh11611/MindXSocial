package com.namanh.kotlinbase.di

import com.namanh.kotlinbase.repository.NewsRepository
import com.namanh.kotlinbase.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}