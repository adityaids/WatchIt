package com.aditya.watchit.data.source.remote

import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper).apply { instance = this }
                }
    }

    fun getPopular(): List<FilmModel> = jsonHelper.loadPopular()

    fun getMovieList(): List<FilmModel> = jsonHelper.loadMovies()

    fun getTvList(): List<FilmModel> = jsonHelper.loadTv()

}