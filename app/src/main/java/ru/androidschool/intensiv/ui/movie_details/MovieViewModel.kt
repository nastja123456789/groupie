package ru.androidschool.intensiv.ui.movie_details

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.androidschool.intensiv.data.dto.MovieModelDB
import ru.androidschool.intensiv.data.dto.MovieRoomDatabase

class MovieViewModel : ViewModel() {
    private val liveHasLike = MutableLiveData<Boolean>()
    val hasLike: LiveData<Boolean> = liveHasLike

    private val liveResId = MutableLiveData<Int>()

    fun setLike(boolean: Boolean) {
        liveHasLike.value = boolean
    }

    fun setImage(context: Context, image: String, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            MovieRoomDatabase.getDatabase(context).movieDao().insert(MovieModelDB(id, image))
        }
    }
    fun deleteImage(context: Context, image: String, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            MovieRoomDatabase.getDatabase(context).movieDao().delete(MovieModelDB(id, image))
        }
    }
    suspend fun exist(context: Context, id: Int):Boolean {
        return withContext(Dispatchers.IO) {
            MovieRoomDatabase.getDatabase(context).movieDao().exists(id)
        }
    }
}