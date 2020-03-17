package com.android.movie.dagger

import androidx.work.CoroutineWorker
import androidx.work.Worker
import com.android.movie.work.ChildWorkerFactory
import com.android.movie.work.RefreshDataWorker
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class WorkerKey(val value: KClass<out CoroutineWorker>)

@Module
abstract class WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    internal abstract fun bindMyWorkerFactory(
        worker:
        RefreshDataWorker.Factory
    ): ChildWorkerFactory
}