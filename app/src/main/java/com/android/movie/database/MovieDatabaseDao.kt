package com.android.movie.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.movie.model.Result
import com.android.movie.network.Movie

@Dao
interface MovieDatabaseDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg movies: DatabaseMovie)

    @Query("UPDATE movie_table SET isFavorite =:isFavorite WHERE id = :key")
     fun unFavorite(isFavorite: Boolean = false, key: Long): Int

    @Query("UPDATE movie_table SET isFavorite =:isFavorite WHERE id = :key")
     fun favorite(isFavorite: Boolean = true, key: Long): Int

    @Query("SELECT * FROM movie_table ORDER BY popularity DESC")
     fun getAll(): LiveData<List<DatabaseMovie>>

    @Query("SELECT * FROM movie_table WHERE id =:id ORDER BY id DESC")
    fun getSingle(id: Int): LiveData<DatabaseMovie>

    @Query("SELECT * FROM movie_table WHERE isFavorite = :isFavorite ORDER BY id DESC")
    fun getFavorite(isFavorite: Boolean = true): LiveData<List<DatabaseMovie>>


}

