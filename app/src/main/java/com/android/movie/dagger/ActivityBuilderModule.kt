package com.android.movie.dagger


import com.android.movie.MainActivity
import com.android.movie.dagger.home.MainFragmentBuilderModule
import com.android.movie.dagger.home.MainModule
import com.android.movie.dagger.home.MainViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {


    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuilderModule::class,
            MainViewModelModule::class,
            MainModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

}