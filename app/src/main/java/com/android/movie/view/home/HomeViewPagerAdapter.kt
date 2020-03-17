package com.android.movie.view.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.movie.view.favoriteMovies.FavoriteMovieFragment
import com.android.movie.view.popularMovies.PopularMovieFragment
import com.android.movie.view.topRatedMovies.TopRatedMovieFragment
import javax.inject.Inject


class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TopRatedMovieFragment()
            1 -> PopularMovieFragment()
            2 -> FavoriteMovieFragment()
            else -> TopRatedMovieFragment()
        }
    }
}


