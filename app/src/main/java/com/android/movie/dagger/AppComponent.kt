package com.android.movie.dagger

import android.app.Application
import com.android.movie.MovieApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        WorkerModule::class
    ]
)
interface AppComponent : AndroidInjector<MovieApplication> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

}