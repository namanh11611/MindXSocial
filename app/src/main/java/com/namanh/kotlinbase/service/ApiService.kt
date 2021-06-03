package com.namanh.kotlinbase.service

import com.namanh.kotlinbase.model.NewsResponse
import com.namanh.kotlinbase.utils.AppUtils
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String,
    ): Call<NewsResponse>

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(AppUtils.BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}