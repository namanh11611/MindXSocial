package com.namanh.kotlinbase.repository

import androidx.lifecycle.MutableLiveData
import com.namanh.kotlinbase.model.News
import com.namanh.kotlinbase.service.ApiService
import com.namanh.kotlinbase.utils.AppUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    apiService: ApiService
) : NewsRepository {

    private var call: Call<List<News>>

    init {
        call = apiService.getNews("us", "business", AppUtils.API_KEY)
    }
    
    override fun getNews(): MutableLiveData<List<News>> {
        val newsResponse = MutableLiveData<List<News>>()

        if (call.isExecuted) {
            call.cancel()
            call = call.clone()
        }
        call.enqueue(object : Callback<List<News>> {
            override fun onResponse(
                call: Call<List<News>>,
                response: Response<List<News>>
            ) {
                val data: List<News>? = response.body()
                if (data != null) {
                    newsResponse.value = data!!
                }
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
            }
        })
        return newsResponse
    }
}