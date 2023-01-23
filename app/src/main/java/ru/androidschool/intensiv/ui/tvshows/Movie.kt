package ru.androidschool.intensiv.ui.tvshows

import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.vo.TVModel

class Movie(
    private val content: TVModel,
) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.findViewById<TextView>(R.id.movieText).text = content.name
        //viewHolder.itemView.findViewById<ImageView>(R.id.movieImage).load(content.posterPath)
        Picasso.get()
            .load(content.posterPath)
            .into(viewHolder.itemView.findViewById<ImageView>(R.id.movieImage))
//        viewHolder.itemView.findViewById<ImageView>(R.id.likeImage).setOnClickListener {
//            Log.e("the end","the end")
//        }
    }
    override fun getLayout(): Int = R.layout.movie_detail_image_fragment
}
