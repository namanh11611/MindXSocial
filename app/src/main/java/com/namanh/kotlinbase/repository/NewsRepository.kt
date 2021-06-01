package com.namanh.kotlinbase.repository

import androidx.lifecycle.MutableLiveData
import com.namanh.kotlinbase.model.News
import javax.inject.Singleton

@Singleton
interface NewsRepository {
    fun getNews(): MutableLiveData<List<News>>
}