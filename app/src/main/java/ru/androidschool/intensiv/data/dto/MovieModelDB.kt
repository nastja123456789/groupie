package ru.androidschool.intensiv.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieModelDB(
    @PrimaryKey
    @ColumnInfo(name = "title")
    val title: String,
)