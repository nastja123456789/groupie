package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.TVModel

class MockRepository : MoviesRepository {

    override fun getMovies(): Single<List<TVModel>> {
        return Single.just(
            listOf(
                TVModel(
                    name = "Agent",
                )
            )
        )
    }
}
