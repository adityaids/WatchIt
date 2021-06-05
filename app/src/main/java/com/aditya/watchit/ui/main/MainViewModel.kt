package com.aditya.watchit.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.data.source.local.entity.PopularEntity
import com.aditya.watchit.vo.Resource

class MainViewModel (private val repository: DataRepository): ViewModel() {
    fun getPopularList(): LiveData<Resource<List<PopularEntity>>> = repository.getAllPopular()
    fun getMovieList(): LiveData<Resource<List<FilmEntity>>> = repository.getAllMovies("Movies")
    fun getTvList(): LiveData<Resource<List<FilmEntity>>> = repository.getAllTv("Tv Series")
}