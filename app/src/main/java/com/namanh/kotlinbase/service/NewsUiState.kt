package com.namanh.kotlinbase.service

import androidx.lifecycle.MutableLiveData
import com.namanh.kotlinbase.model.NewsResponse

sealed class NewsUiState {
    data class Success(val response: MutableLiveData<NewsResponse>) : NewsUiState()
    data class Error(val exception: Throwable): NewsUiState()
    object Loading: NewsUiState()
}