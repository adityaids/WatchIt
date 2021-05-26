package com.aditya.watchit.data.source.remote

import android.os.Handler
import android.os.Looper
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.utils.EspressoIdlingResource
import com.aditya.watchit.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val DUMMY_SERVICE_LATENCY: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper).apply { instance = this }
                }
    }

    fun getPopular(callback: LoadPopularCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllPopularReceived(jsonHelper.loadPopular())
            EspressoIdlingResource.decrement()
         }, DUMMY_SERVICE_LATENCY)
    }

    fun getMovieList(callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllMovieReceived(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
         }, DUMMY_SERVICE_LATENCY)
    }

    fun getTvList(callback: LoadTvCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllTvReceived(jsonHelper.loadTv())
            EspressoIdlingResource.decrement()
        }, DUMMY_SERVICE_LATENCY)
    }

    fun getFilm(title: String, type: String, callback: LoadFilmCallback){
        callback.onFilmReceived(jsonHelper.loadFilm(title, type))
    }

    interface LoadPopularCallback {
        fun onAllPopularReceived(popularResponse: List<FilmModel>)
    }
    interface LoadMovieCallback {
        fun onAllMovieReceived(movieResponses: List<FilmModel>)
    }
    interface LoadTvCallback {
        fun onAllTvReceived(tvResponse: List<FilmModel>)
    }
    interface LoadFilmCallback{
        fun onFilmReceived(filmModel: FilmModel)
    }
}