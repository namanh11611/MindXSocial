package com.namanh.kotlinbase.di

import com.namanh.kotlinbase.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    fun providesApiService(): ApiService = ApiService.create()
}