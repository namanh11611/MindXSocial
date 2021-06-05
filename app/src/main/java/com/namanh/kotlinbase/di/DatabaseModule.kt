package com.namanh.kotlinbase.di

import android.content.Context
import androidx.room.Room
import com.namanh.kotlinbase.database.AppDatabase
import com.namanh.kotlinbase.database.NewsDao
import com.namanh.kotlinbase.utils.AppUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppUtils.DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun providesNewsDao(appDatabase: AppDatabase): NewsDao = appDatabase.newsDao()
}