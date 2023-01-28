package ru.androidschool.intensiv.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class MovieModelDB(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
)