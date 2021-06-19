package com.namanh.kotlinbase.view.list

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namanh.kotlinbase.model.NewsResponse
import com.namanh.kotlinbase.repository.NewsRepository
import com.namanh.kotlinbase.service.NewsUiState
import com.namanh.kotlinbase.utils.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsRepo : NewsRepository
): ViewModel() {

//    private var newsResponse: MutableLiveData<NewsResponse> = newsRepo.getNews()
    private lateinit var newsResponse: MutableLiveData<NewsResponse>

    private val _uiState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    val uiState: StateFlow<NewsUiState> = _uiState

    fun getNews(): MutableLiveData<NewsResponse> {
        LogUtil.d("viewmodel getnew out")
        viewModelScope.launch {
            LogUtil.d("viewmodel getnew in")
            val newsRes = newsRepo.getNews()
            _uiState.value = NewsUiState.Success(newsRes)
//            newsResponse = newsRepo.getNews()
        }
        return newsResponse
    }

    fun fetchNews(viewLifecycleOwner: LifecycleOwner) {
        viewModelScope.launch {
            newsRepo.getNews().observe(viewLifecycleOwner, {
                newsResponse.value = it
            })
        }
    }
//    fun getNews() = newsResponse

}