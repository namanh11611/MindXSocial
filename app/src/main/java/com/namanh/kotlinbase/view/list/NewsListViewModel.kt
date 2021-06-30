package com.namanh.kotlinbase.view.list

import androidx.lifecycle.*
import com.namanh.kotlinbase.data.model.NewsResponse
import com.namanh.kotlinbase.data.repository.NewsRepository
import com.namanh.kotlinbase.data.repository.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsRepo : NewsRepository
): ViewModel() {

    private val _forceUpdate = MutableLiveData(true)

    val items: LiveData<NewsResponse> = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            viewModelScope.launch {
                newsRepo.getNews()
            }
        }
        newsRepo.observeNews(viewModelScope.coroutineContext).distinctUntilChanged().switchMap {
            handleNews(it)
        }
    }

    private fun handleNews(newsResponse: ResourceState<NewsResponse>): LiveData<NewsResponse> {
        val result = MutableLiveData<NewsResponse>()
        when (newsResponse) {
            is ResourceState.Success -> result.value = newsResponse.getCurrentData()
        }
        return result
    }

}