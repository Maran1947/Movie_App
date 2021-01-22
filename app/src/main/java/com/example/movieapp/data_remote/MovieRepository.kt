package com.example.movieapp.data_remote

import com.example.movieapp.data_local.entity.MovieResponse

interface MovieRepository {
    fun fetchMovies(api_key: String, onSuccess:(MovieResponse) -> Unit, onError:(String) -> Unit)

    fun getMoviesLocal(onSuccess: (MovieResponse?) -> Unit)
}