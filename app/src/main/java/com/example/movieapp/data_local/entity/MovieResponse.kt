package com.example.movieapp.data_local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.movieapp.typeconverter.MovieTypeConverter


@Entity(tableName = "tbl_movie_data")
data class MovieResponse(

    @PrimaryKey
    val page:Int = 1,

    @ColumnInfo(name = "movie_response")
    @TypeConverters(MovieTypeConverter::class)
    val results: List<Movie>
)