package com.namanh.kotlinbase.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.namanh.kotlinbase.model.News
import com.namanh.kotlinbase.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    newsRepo : NewsRepository
): ViewModel() {

    private var newsResponse: MutableLiveData<List<News>> = newsRepo.getNews()

    fun getNews() = newsResponse

}