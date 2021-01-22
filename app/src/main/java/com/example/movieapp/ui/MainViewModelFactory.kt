package com.example.movieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.NetworkHelper
import com.example.movieapp.data_remote.MovieRepository

class MainViewModelFactory(private val networkHelper: NetworkHelper,
                           private val movieRepository: MovieRepository):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(networkHelper, movieRepository) as T
    }
}