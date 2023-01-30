package ru.androidschool.intensiv.data.dto

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieModelDao {
    @Query("SELECT * from Movie ORDER BY title ASC")
    fun getAllMovies() : List<MovieModelDB>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movieModelDB: MovieModelDB)
    @Delete
    fun delete(movieModelDB: MovieModelDB)
    @Query("SELECT EXISTS (SELECT 1 FROM Movie WHERE id = :id)")
    fun exists(id: Int): Boolean
}