package com.namanh.kotlinbase.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.namanh.kotlinbase.data.database.NewsDao
import com.namanh.kotlinbase.data.model.NewsResponse
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

    private val mNewsResponse = MutableLiveData<ResourceState<NewsResponse>>()

    override suspend fun getNews() {
        val res = getNewsFromRemote()
        LogUtil.d("res $res")
        mNewsResponse.value = res
    }

    override fun observeNews(coroutineContext: CoroutineContext): LiveData<ResourceState<NewsResponse>> {
        return mNewsResponse
    }

    private suspend fun getNewsFromRemote(): ResourceState<NewsResponse> = withContext(dispatcherIO) {
        try {
            val response = apiService.getNews("us", "business", AppUtils.API_KEY)
            ResourceState.Success(response)
        } catch (exception: Exception) {
            ResourceState.Error("Exception ${exception.message}")
        }
    }
}