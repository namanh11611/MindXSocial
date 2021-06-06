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

    private val _uiState = MutableStateFlow<NewsUiState>(NewsUiState.LOADING)
    val uiState: StateFlow<NewsUiState> = _uiState

    fun fetchNews(viewLifecycleOwner: LifecycleOwner) {
        viewModelScope.launch {
            newsRepo.getNews().observe(viewLifecycleOwner, {
                newsResponse.value = it
            })
        }
    }

//    fun getNews() = newsResponse
    fun getNews(): MutableLiveData<NewsResponse> {
        LogUtil.d("viewmodel getnew out")
        viewModelScope.launch {
            _uiState.value = NewsUiState.SUCCESS()
            LogUtil.d("viewmodel getnew in")
            newsResponse = newsRepo.getNews()
        }
        return newsResponse
    }

//        return withContext(Dispatchers.IO) {
//            LogUtil.d("viewmodel getnew in")
//            newsResponse = newsRepo.getNews()
//            return@withContext newsResponse
//        }

}