package com.android.movie.view.movieDetails

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.android.movie.R
import com.android.movie.database.DatabaseMovie
import com.android.movie.databinding.FragmentMovieDetailBinding
import com.android.movie.util.Utility
import com.android.movie.util.Utility.IMAGE_URL
import com.android.movie.util.Utility.YOUTUBE_API_KEY
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.like.LikeButton
import com.like.OnLikeListener
import com.squareup.picasso.Picasso
import timber.log.Timber
import java.lang.String


const val MOVIE = "movie"
const val RECOVERY_REQUEST = 1

class MovieDetailsFragment :  Fragment()/*, YouTubePlayer.OnInitializedListener */ {

    private lateinit var factory: MovieDetailViewModelFactory
    private lateinit var detailFragmentViewModel: MovieDetailFragmentViewModel
    private lateinit var binding: FragmentMovieDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        factory = MovieDetailViewModelFactory(arguments?.get(MOVIE) as DatabaseMovie, application)
        detailFragmentViewModel =
            ViewModelProvider(this, factory).get(MovieDetailFragmentViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_detail, container, false
        )
        //binding.youtubeView.initialize(YOUTUBE_API_KEY, this)
        binding.detailViewModel = detailFragmentViewModel
        binding.lifecycleOwner = this
        binding.backBtn.setOnClickListener { findNavController().navigateUp() }
        detailFragmentViewModel.movie.observe(viewLifecycleOwner, Observer {
            bindView(binding, it)
        })

       // Utility.hideSoftKeyBoard(context!!, binding.backBtn)

        return binding.root


    }

    private fun bindView(binding: FragmentMovieDetailBinding, it: DatabaseMovie) {
        binding.title.text = it.title
        binding.overview.text = it.overview
        binding.starButton.isLiked = it.isFavorite
        binding.starButton.click(detailFragmentViewModel, it)
        binding.rating.rating = it.voteAverage?.div(2)?.toFloat()?: 0.toFloat()
        binding.releaseDate.append( " ${it.releaseDate}")
        Picasso.get()
            .load(IMAGE_URL + it.backdropPath)
            .error(R.drawable.image_failed)
            .into(binding.expandedImage)
    }
/*
    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider,
        player: YouTubePlayer,
        wasRestored: Boolean
    ) {
        if (!wasRestored) {
            player.cueVideo("fhWaJi1Hsfo"); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }

    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider,
        errorReason: YouTubeInitializationResult
    ) {
       *//* if (errorReason.isUserRecoverableError) {
            errorReason.getErrorDialog(context, RECOVERY_REQUEST).show()
        } else {
            val error =
                String.format(getString(R.string.player_error), errorReason.toString())

        }*//*
        Timber.i(errorReason.toString())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            binding.youtubeView.initialize(YOUTUBE_API_KEY, this)
        }*/
    }





private fun LikeButton.click(
    detailFragmentViewModel: MovieDetailFragmentViewModel,
    it: DatabaseMovie
) {
    setOnLikeListener(object : OnLikeListener {
        override fun liked(likeButton: LikeButton) {
            detailFragmentViewModel.setFavorite(it.id)
        }

        override fun unLiked(likeButton: LikeButton) {
            detailFragmentViewModel.removeFavorite(it.id)
        }
    })
}