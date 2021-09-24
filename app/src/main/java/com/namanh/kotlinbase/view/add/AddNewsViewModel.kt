package com.namanh.kotlinbase.view.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namanh.kotlinbase.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewsViewModel @Inject constructor(
    private val newsRepo: NewsRepository
) : ViewModel() {

    fun createNews(author: String, content: String) {
        viewModelScope.launch {
            newsRepo.createNews(author, content)
        }
    }

}