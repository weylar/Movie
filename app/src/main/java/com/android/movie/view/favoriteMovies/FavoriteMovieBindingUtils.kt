package com.android.movie.view.favoriteMovies

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import com.android.movie.R
import com.android.movie.database.DatabaseMovie
import com.android.movie.model.Result
import com.android.movie.util.Utility.IMAGE_URL
import com.like.LikeButton
import com.squareup.picasso.Picasso
import timber.log.Timber


@BindingAdapter("image")
fun ImageView.setPosterImage(item: DatabaseMovie?) {
    item?.let {
        Timber.i(item.posterPath)
        val builder = Picasso.Builder(context)
        builder.build().load(IMAGE_URL + item.posterPath)
            .into(this)
    }

}


@BindingAdapter("title")
fun TextView.setTitle(item: DatabaseMovie?) {
    item?.let {
        text = item.title
    }

}








