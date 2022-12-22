//package ru.androidschool.intensiv.ui.feed
//
//import androidx.lifecycle.LiveData
//
//class MovieRepository(private val movieModelDao: MovieModelDao) {
//    val allMovies: LiveData<List<MovieModelDB>> =
//        movieModelDao.getAllMovies()
//
//    suspend fun insert(movieModelDB: MovieModelDB) {
//        movieModelDao.insert(movieModelDB)
//    }
//}