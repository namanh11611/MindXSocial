package com.namanh.mindx.data.repository

import androidx.lifecycle.LiveData
import com.namanh.mindx.data.model.News
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
interface NewsRepository {

    suspend fun getNews()

    suspend fun createNews(author: String, content: String)

    fun observeNews(coroutineContext: CoroutineContext): LiveData<ResourceState<List<News>>>

}