package com.namanh.kotlinbase.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.namanh.kotlinbase.data.database.NewsDao
import com.namanh.kotlinbase.data.model.News
import com.namanh.kotlinbase.data.service.ApiService
import com.namanh.kotlinbase.di.DispatcherIO
import com.namanh.kotlinbase.utils.AppUtils
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
        val remoteNews = getNewsFromRemote()
        if (remoteNews is ResourceState.Success) {
            LogUtil.d("remoteNews $remoteNews")
            mNewsResponse.value = remoteNews
        }
    }

    override fun observeNews(coroutineContext: CoroutineContext): LiveData<ResourceState<List<News>>> {
        return mNewsResponse
    }

    suspend fun getNewsFromDatabase(): ResourceState<List<News>> = withContext(dispatcherIO) {
        try {
            ResourceState.Success(newsDao.getAll())
        } catch (exception: Exception) {
            LogUtil.e("Get news from database exception: ${exception.message}")
            ResourceState.Error("Database exception: ${exception.message}")
        }
    }

    private suspend fun getNewsFromRemote(): ResourceState<List<News>> = withContext(dispatcherIO) {
        try {
            val response = apiService.getNews("us", "business", AppUtils.API_KEY)
            ResourceState.Success(response.articles)
        } catch (exception: Exception) {
            LogUtil.e("Get news from remote exception: ${exception.message}")
            ResourceState.Error("Remote exception: ${exception.message}")
        }
    }

    private suspend fun saveNewsToDatabase(news: List<News>) = withContext(dispatcherIO) {
        try {
            newsDao.deleteAll()
            newsDao.insertAll(news)
        } catch (exception: Exception) {
            LogUtil.e("Save database Exception: ${exception.message}")
        }
    }
}