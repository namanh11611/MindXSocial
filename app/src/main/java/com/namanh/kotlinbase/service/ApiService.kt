package com.namanh.kotlinbase.service

import com.namanh.kotlinbase.model.News
import com.namanh.kotlinbase.utils.AppUtils
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET()
    fun getNews(): Call<List<News>>

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