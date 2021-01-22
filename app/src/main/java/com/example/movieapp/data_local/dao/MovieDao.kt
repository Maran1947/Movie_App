package com.example.movieapp.data_local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data_local.entity.MovieResponse

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieResponse: MovieResponse)

    @Query("select * from tbl_movie_data")
    fun getMovies(): MovieResponse
}