package ru.androidschool.intensiv.ui.feed

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_db")
data class MovieModelDB(
    @PrimaryKey
    @ColumnInfo(name = "title")
    val title: String,
)