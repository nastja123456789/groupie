//package ru.androidschool.intensiv.ui.feed
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.launch
//
//class MovieViewModel(application: Application) :
//AndroidViewModel(application){
//    var  repository: MovieRepository
//    var allMovie:LiveData<List<MovieModelDB>>
//    init {
//        val moviesDao = MovieRoomDatabase.getDatabase(application).movieDao()
//        repository = MovieRepository(moviesDao)
//        allMovie = repository.allMovies
//    }
//
//    fun insert(movie: MovieModelDB) = viewModelScope.launch {
//        repository.insert(movie)
//    }
//}