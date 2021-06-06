package com.namanh.kotlinbase.repository

import androidx.lifecycle.MutableLiveData
import com.namanh.kotlinbase.database.NewsDao
import com.namanh.kotlinbase.database.NewsData
import com.namanh.kotlinbase.model.NewsResponse
import com.namanh.kotlinbase.service.ApiService
import com.namanh.kotlinbase.utils.AppUtils
import com.namanh.kotlinbase.utils.LogUtil
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    apiService: ApiService,
    private val newsDao: NewsDao,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : NewsRepository {

    private var call: Call<NewsResponse>

    init {
        call = apiService.getNews("us", "business", AppUtils.API_KEY)
    }
    
    override suspend fun getNews(): MutableLiveData<NewsResponse> {
        return withContext(defaultDispatcher) {
            LogUtil.d("repo getNews")
            val newsResponse = MutableLiveData<NewsResponse>()

            if (call.isExecuted) {
                call.cancel()
                call = call.clone()
            }
            call.enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    val data: NewsResponse? = response.body()
                    if (data != null) {
                        newsResponse.value = data!!

                        if (data.articles.isNotEmpty()) {
                            for ((index, news) in data.articles.withIndex()) {
                                GlobalScope.launch {
                                    newsDao.insertAll(
                                        NewsData(
                                            index,
                                            news.author,
                                            news.title,
                                            news.description,
                                            news.url,
                                            news.urlToImage,
                                            news.publishedAt
                                        )
                                    )
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    LogUtil.e("Fail: ${t.message}")
                }
            })
//            return newsResponse
            return@withContext newsResponse
        }
    }
}