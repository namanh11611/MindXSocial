package com.namanh.kotlinbase.repository

import androidx.lifecycle.MutableLiveData
import com.namanh.kotlinbase.model.NewsResponse
import javax.inject.Singleton

@Singleton
interface NewsRepository {
    suspend fun getNews(): MutableLiveData<NewsResponse>
}