package ru.androidschool.intensiv.ui.feed

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieApiClient {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    val apiClient: MovieApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return@lazy retrofit.create(MovieApiInterface::class.java)
    }
    val apiClientRecommended: MovieApiInterfaceRecommended by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return@lazy retrofit.create(MovieApiInterfaceRecommended::class.java)
    }
    val apiClientPopular: tvPopularInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return@lazy retrofit.create(tvPopularInterface::class.java)
    }
}