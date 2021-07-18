package com.namanh.kotlinbase.data.repository

import androidx.lifecycle.LiveData
import com.namanh.kotlinbase.data.model.News
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
interface NewsRepository {

    suspend fun getNews()

    fun observeNews(coroutineContext: CoroutineContext): LiveData<ResourceState<List<News>>>

}