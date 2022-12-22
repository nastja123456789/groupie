package ru.androidschool.intensiv.ui.feed

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MovieModelDB::class), version = 1, exportSchema = false)
public abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieModelDao
    companion object {
        // Используется паттерн синглтон для одного экземпляра класса базы данных
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null
        fun getDatabase(context: Context): MovieRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    "movie_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}