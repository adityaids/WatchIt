package com.aditya.watchit.data

interface FilmDataSource {
    fun getAllPopular(): ArrayList<FilmModel>
    fun getAllMovies(): ArrayList<FilmModel>
    fun getAllTv(): ArrayList<FilmModel>
}