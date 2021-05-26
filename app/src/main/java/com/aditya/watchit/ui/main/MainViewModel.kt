package com.aditya.watchit.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.utils.DummyData

class MainViewModel (private val repository: DataRepository): ViewModel() {
    fun getPopularList(): LiveData<List<FilmModel>> = repository.getAllPopular()
    fun getMovieList(): LiveData<List<FilmModel>> = repository.getAllMovies()
    fun getTvList(): LiveData<List<FilmModel>> = repository.getAllTv()
}