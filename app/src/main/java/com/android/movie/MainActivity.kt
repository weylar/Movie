package com.android.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.movie.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //refresh()
    }

    private fun refresh() {
        CoroutineScope(Main).launch {
            MovieRepository(application).refreshMovies()
        }
    }
}
