package ru.androidschool.intensiv.ui.feed

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieModelDao {
    @Query("SELECT * from movie_db ORDER BY title ASC")
    fun getAllMovies() : List<MovieModelDB>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movieModelDB: MovieModelDB)
}