package com.namanh.kotlinbase.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.namanh.kotlinbase.data.database.NewsDao
import com.namanh.kotlinbase.data.model.News
import com.namanh.kotlinbase.data.service.ApiService
import com.namanh.kotlinbase.di.DispatcherIO
import com.namanh.kotlinbase.utils.LogUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : NewsRepository {

    private val mNewsResponse = MutableLiveData<ResourceState<List<News>>>()

    override suspend fun getNews() {
        val newsList = getNewsFromDatabase()
        LogUtil.d("localNews success")
        mNewsResponse.value = newsList
    }


    override suspend fun createNews(author: String, content: String) {
        newsDao.insert(News(0, author, "", content, "", "", "", ""))
    }

    override fun observeNews(coroutineContext: CoroutineContext): LiveData<ResourceState<List<News>>> {
        return mNewsResponse
    }

    private suspend fun getNewsFromDatabase(): ResourceState<List<News>> = withContext(dispatcherIO) {
        try {
            ResourceState.Success(newsDao.getAll())
        } catch (exception: Exception) {
            LogUtil.e("Get news from database exception: ${exception.message}")
            ResourceState.Error("Database exception: ${exception.message}")
        }
    }
}