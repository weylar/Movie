package com.android.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.movie.repository.MovieRepository
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var movieRepository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //refresh()
    }

    private fun refresh() {
        CoroutineScope(Main).launch {
            movieRepository.refreshMovies()
        }
    }
}
