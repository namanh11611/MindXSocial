package com.namanh.mindx.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.namanh.mindx.data.model.News
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedViewModel @Inject constructor() : ViewModel() {
    private val mNews = MutableLiveData<News>()

    fun selectNews(news: News) {
        mNews.value = news
    }

    fun getNews(): News? {
        return mNews.value
    }
}