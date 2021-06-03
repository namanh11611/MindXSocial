package com.namanh.kotlinbase.view.list

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.namanh.kotlinbase.model.NewsResponse
import com.namanh.kotlinbase.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsRepo : NewsRepository
): ViewModel() {

    private var newsResponse: MutableLiveData<NewsResponse> = newsRepo.getNews()

    fun fetchNews(viewLifecycleOwner: LifecycleOwner) {
        newsRepo.getNews().observe(viewLifecycleOwner, {
            newsResponse.value = it
        })
    }

    fun getNews() = newsResponse

}