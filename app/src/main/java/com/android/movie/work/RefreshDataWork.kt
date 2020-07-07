package com.android.movie.work

/*
* Created by Idris Aminu O. on 20-2-20
* */
import android.app.Application
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.android.movie.repository.MovieRepository
import com.android.movie.source.MovieLocalDataSource
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class RefreshDataWorker @Inject constructor(
    appContext: Context,
    params: WorkerParameters,
    val repository: MovieRepository
) :
    CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        Timber.i("I'm happening")
        return try {

            repository.refreshMovies()
            Result.success()
        } catch (e: HttpException) {
            Result.failure()
        }
    }

    companion object {
        const val WORKER_NAME = "RefreshDataWork"
    }

    class Factory @Inject constructor(
        val repository: MovieRepository
        ): ChildWorkerFactory {

        override fun create(appContext: Context, params: WorkerParameters): CoroutineWorker {
            return RefreshDataWorker(appContext, params, repository)
        }
    }
}
