package ru.androidschool.intensiv.ui.watchlist

import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.databinding.ItemSmallBinding
import ru.androidschool.intensiv.data.dto.MovieModelDB

class MoviePreviewItem(
    private val content: MovieModelDB,
    private val onClick: (movie: Movie) -> Unit
) : BindableItem<ItemSmallBinding>() {

    override fun getLayout() = R.layout.item_small

    override fun bind(view: ItemSmallBinding, position: Int) {
//        view.titleView.text = content.title
//        view.imagePreview.setOnClickListener {
//            onClick.invoke(content)
//        }
        // TODO Получать из модели
        Picasso.get()
            .load(content.title)
            .into(view.imagePreview)
    }

    override fun initializeViewBinding(v: View): ItemSmallBinding = ItemSmallBinding.bind(v)
}
