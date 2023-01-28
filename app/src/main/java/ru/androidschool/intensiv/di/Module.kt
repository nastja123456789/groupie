package ru.androidschool.intensiv.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.domain.repository.MockRepository
import ru.androidschool.intensiv.domain.repository.MoviesRepository
import ru.androidschool.intensiv.domain.repository.TopRatedMoviesRemoteRepository
import ru.androidschool.intensiv.domain.usecase.TopRatedMoviesUseCase
import ru.androidschool.intensiv.ui.tvshows.TvShowsPresenter

val dataModule = module {
    single{
        MovieApiClient.apiClient
    }
}

val domainModule = module {
    single<MoviesRepository> { TopRatedMoviesRemoteRepository() }
}

val presentationModule = module {
    factory { TvShowsPresenter() }
}