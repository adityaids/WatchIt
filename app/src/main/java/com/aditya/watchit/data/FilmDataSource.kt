package com.aditya.watchit.data

import androidx.lifecycle.LiveData

interface FilmDataSource {
    fun getAllPopular(): LiveData<List<FilmModel>>
    fun getAllMovies(): LiveData<List<FilmModel>>
    fun getAllTv(): LiveData<List<FilmModel>>
    fun getFilm(title: String, type: String): LiveData<FilmModel>
}