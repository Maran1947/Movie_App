package com.example.movieapp.data_local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.data_local.dao.MovieDao
import com.example.movieapp.data_local.entity.MovieResponse
import com.example.movieapp.typeconverter.MovieTypeConverter

@Database(entities = [MovieResponse::class], version = 1)
@TypeConverters(MovieTypeConverter::class)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object{
        private const val DATABASE_NAME = "movie-app"

        @Volatile
        private var INSTANCE : MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            synchronized(this){
                var instance: MovieDatabase? = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext, MovieDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}