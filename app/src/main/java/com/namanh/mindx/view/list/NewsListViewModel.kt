package com.namanh.mindx.view.list

import androidx.lifecycle.*
import com.namanh.mindx.data.model.News
import com.namanh.mindx.data.repository.NewsRepository
import com.namanh.mindx.data.repository.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsRepo: NewsRepository
) : ViewModel() {

    private val _forceUpdate = MutableLiveData(true)

    val items: LiveData<ResourceState<List<News>>> = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            viewModelScope.launch {
                newsRepo.getNews()
            }
        }
        newsRepo.observeNews(viewModelScope.coroutineContext).distinctUntilChanged()
    }

    fun forceUpdate() {
        _forceUpdate.value = true
    }

}