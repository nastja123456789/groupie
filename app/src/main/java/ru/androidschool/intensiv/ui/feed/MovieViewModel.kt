import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.androidschool.intensiv.R

class MovieViewModel() : ViewModel() {
    private val liveHasLike = MutableLiveData<Boolean>()
    val hasLike: LiveData<Boolean> = liveHasLike

    private val liveResId = MutableLiveData<Int>()

    fun setLike(boolean: Boolean) {
        liveHasLike.value = boolean
    }
}