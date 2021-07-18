package com.namanh.kotlinbase.view.list

import androidx.lifecycle.*
import com.namanh.kotlinbase.data.model.News
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

    val items: LiveData<List<News>> = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            viewModelScope.launch {
                newsRepo.getNews()
            }
        }
        newsRepo.observeNews(viewModelScope.coroutineContext).distinctUntilChanged().switchMap {
            handleNews(it)
        }
    }

    private fun handleNews(newsResponse: ResourceState<List<News>>): LiveData<List<News>> {
        val result = MutableLiveData<List<News>>()
        when (newsResponse) {
            is ResourceState.Success -> result.value = newsResponse.getCurrentData()
        }
        return result
    }

    fun forceUpdate() {
        _forceUpdate.value = true
    }

}