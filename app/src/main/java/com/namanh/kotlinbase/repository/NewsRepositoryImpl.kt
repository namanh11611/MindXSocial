package com.namanh.kotlinbase.repository

import androidx.lifecycle.MutableLiveData
import com.namanh.kotlinbase.model.NewsResponse
import com.namanh.kotlinbase.service.ApiService
import com.namanh.kotlinbase.utils.AppUtils
import com.namanh.kotlinbase.utils.LogUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    apiService: ApiService
) : NewsRepository {

    private var call: Call<NewsResponse>

    init {
        call = apiService.getNews("us", "business", AppUtils.API_KEY)
    }
    
    override fun getNews(): MutableLiveData<NewsResponse> {
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
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                LogUtil.e("Fail: ${t.message}")
            }
        })
        return newsResponse
    }
}