package com.namanh.mindx.di

import android.content.Context
import androidx.room.Room
import com.namanh.mindx.data.database.AppDatabase
import com.namanh.mindx.data.database.NewsDao
import com.namanh.mindx.data.repository.NewsRepository
import com.namanh.mindx.data.repository.NewsRepositoryImpl
import com.namanh.mindx.data.service.ApiService
import com.namanh.mindx.utils.AppUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DispatcherIO

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesApiService(): ApiService =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(AppUtils.BASE_URL)
            .build().create(ApiService::class.java)

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

    @DispatcherIO
    @Singleton
    @Provides
    fun providesDispatcherIO(): CoroutineDispatcher = Dispatchers.IO
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}