package ru.androidschool.intensiv.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.androidschool.intensiv.data.vo.MovieModel
import ru.androidschool.intensiv.domain.repository.SearchRepository

class SearchFragmentViewModel(private val repository: SearchRepository) : ViewModel() {

    val searchMoviesLiveData = MutableLiveData<List<MovieModel>>()

    private fun searchByQuery(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = repository.searchByQuery(query)
            searchMoviesLiveData.postValue(movies)
        }
    }
}