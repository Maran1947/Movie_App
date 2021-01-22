package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.data_local.MovieDatabase
import com.example.movieapp.data_local.entity.Movie
import com.example.movieapp.data_remote.MovieRepositoryImpl
import com.example.movieapp.data_remote.retrofit.RetrofitBuilder
import com.example.movieapp.ui.MainViewModel
import com.example.movieapp.ui.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViewModel()
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.movieResponse.observe(this, Observer {
            showMovies(it.results)
            hideProgress()
        })

        viewModel.errorResponse.observe(this, Observer {
            showErrorMessage(it)
            hideProgress()
        })
    }

    private fun setUpViewModel() {

        showProgress()

        viewModel = ViewModelProvider(this, MainViewModelFactory(
            NetworkHelper(this),
            MovieRepositoryImpl(
                MovieDatabase.getInstance(this).movieDao(),
                RetrofitBuilder.buildService())
        ))[MainViewModel::class.java]
        viewModel.onCreate()
    }

    private fun showMovies(movies: List<Movie>){
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = MovieAdapter(movies)
    }

    private fun showProgress(){
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress(){
        progressBar.visibility = View.GONE
    }

    private fun showErrorMessage(errorMessage: String?){
        errorView.visibility = View.VISIBLE
        errorView.text = errorMessage
    }
}