package ru.androidschool.intensiv.ui.feed

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {
    @GET("movie/upcoming")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ) : Single<MoviesResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ) : Single<MoviesResponse>

    @GET("tv/popular")
    fun getPopularTV(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ) : Single<TVResponse>

    @GET("search/movie")
    fun searchByQuery(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ) : Single<MoviesResponse>
}