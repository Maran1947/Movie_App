package com.example.movieapp.data_remote

import com.example.movieapp.*
import com.example.movieapp.data_local.dao.MovieDao
import com.example.movieapp.data_local.entity.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl(
    private val movieDao: MovieDao,
    private val request: MovieService
): MovieRepository {

    override fun fetchMovies(
        api_key: String,
        onSuccess: (MovieResponse) -> Unit,
        onError: (String) -> Unit
    ) {

        val call: Call<MovieResponse> = request.getMovies(api_key)

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if(response.isSuccessful  && response.body() != null) {
                    Thread{
                        movieDao.insertMovie(response.body()!!)
                        onSuccess(response.body()!!)
                    }.start()
                } else{

                    onError(response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // error
                onError("Oops! something went wrong")
            }
        })
    }

    override fun getMoviesLocal(onSuccess: (MovieResponse?) -> Unit) {
        Thread{
            onSuccess(movieDao.getMovies())
        }.start()
    }

}