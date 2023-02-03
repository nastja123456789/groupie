package ru.androidschool.intensiv

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.androidschool.intensiv.data.dto.MovieModelDB
import ru.androidschool.intensiv.data.mappers.MovieMapper

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun movieToViewObject() {
        val movie = MovieMapper.toView(
            movieModelDB = MovieModelDB(1, "новость")
        )
        assertEquals(movie.title, BEFORE)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    companion object {
        const val BEFORE = "новость"
    }
}