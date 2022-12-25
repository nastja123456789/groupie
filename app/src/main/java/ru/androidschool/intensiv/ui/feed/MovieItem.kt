package ru.androidschool.intensiv.ui.feed

import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.Movie
import ru.androidschool.intensiv.databinding.ItemWithTextBinding

class MovieItem(
    private val content: MovieModel,
    private val onClick: (movie: MovieModel) -> Unit
) : BindableItem<ItemWithTextBinding>() {

    override fun getLayout(): Int = R.layout.item_with_text

    override fun bind(view: ItemWithTextBinding, position: Int) {
        view.description.text = content.title
        view.movieRating.rating = content.voteAverage!!.toFloat()
        view.content.setOnClickListener {
            onClick.invoke(content)
        }

        // TODO Получать из модели
        Picasso.get()
            .load(content.posterPath)
            .into(view.imagePreview)
    }

    override fun initializeViewBinding(v: View) = ItemWithTextBinding.bind(v)
}
