package ru.androidschool.intensiv.ui.feed

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterfaceRecommended {
    @GET("movie/now_playing")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ) : Call<MoviesResponse>
}