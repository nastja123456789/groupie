package ru.androidschool.intensiv.network

import ru.androidschool.intensiv.ui.feed.MoviesResponse
import ru.androidschool.intensiv.ui.feed.TVResponse

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {
    @GET("movie/upcoming")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ) : Observable<MoviesResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ) : Observable<MoviesResponse>

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
