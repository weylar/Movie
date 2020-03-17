package com.android.movie.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.movie.database.MovieDatabaseDao
import com.android.movie.model.Result


@Database(entities = [DatabaseMovie::class], version = 10, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {


    abstract val movieDatabaseDao: MovieDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            MovieDatabase::class.java,
                            "movie_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
