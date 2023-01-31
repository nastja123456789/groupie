package ru.androidschool.intensiv.ui.search
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.MoviesResponse
import ru.androidschool.intensiv.domain.repository.SearchRepository

class SearchFragmentViewModel(private val repository: SearchRepository) : ViewModel() {

    fun search(query: String):Single<MoviesResponse> {
        return repository.search(query)
    }
}