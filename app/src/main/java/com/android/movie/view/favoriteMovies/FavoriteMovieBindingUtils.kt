package com.android.movie.view.favoriteMovies

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.android.movie.database.DatabaseMovie
import com.android.movie.util.Utility.IMAGE_URL
import com.squareup.picasso.Picasso
import javax.inject.Inject



@BindingAdapter("image")
fun ImageView.setPosterImage(item: DatabaseMovie?) {
    item?.let {
        Picasso.get().load(IMAGE_URL + item.posterPath)
            .into(this)
    }

}


@BindingAdapter("title")
fun TextView.setTitle(item: DatabaseMovie?) {
    item?.let {
        text = item.title
    }

}








