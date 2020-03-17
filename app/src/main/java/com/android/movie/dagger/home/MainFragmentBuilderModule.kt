package com.android.movie.dagger.home


import com.android.movie.view.favoriteMovies.FavoriteMovieFragment
import com.android.movie.view.home.HomeFragment
import com.android.movie.view.movieDetails.MovieDetailsFragment
import com.android.movie.view.popularMovies.PopularMovieFragment
import com.android.movie.view.topRatedMovies.TopRatedMovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun movieDetailsFragment(): MovieDetailsFragment

    @ContributesAndroidInjector
    abstract fun popularMovieFragment(): PopularMovieFragment

    @ContributesAndroidInjector
    abstract fun favoriteMovieFragment(): FavoriteMovieFragment

    @ContributesAndroidInjector
    abstract fun topRatedMovieFragment(): TopRatedMovieFragment
}