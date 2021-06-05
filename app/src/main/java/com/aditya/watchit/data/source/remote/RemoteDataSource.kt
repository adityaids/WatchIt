package com.aditya.watchit.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getPopular(): LiveData<ApiResponse<List<FilmModel>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<FilmModel>>>()
        handler.postDelayed({
            result.value = ApiResponse.success(jsonHelper.loadPopular())
            EspressoIdlingResource.decrement()
         }, DUMMY_SERVICE_LATENCY)
        return result
    }

    fun getMovieList(): LiveData<ApiResponse<List<FilmModel>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<FilmModel>>>()
        handler.postDelayed({
            result.value = ApiResponse.success(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, DUMMY_SERVICE_LATENCY)
        return result
    }

    fun getTvList(): LiveData<ApiResponse<List<FilmModel>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<FilmModel>>>()
        handler.postDelayed({
            result.value = ApiResponse.success(jsonHelper.loadTv())
            EspressoIdlingResource.decrement()
        }, DUMMY_SERVICE_LATENCY)
        return result
    }

    fun getFilm(title: String, type: String): LiveData<ApiResponse<FilmModel>>{
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<FilmModel>>()
        handler.postDelayed({
            result.value = ApiResponse.success(jsonHelper.loadFilm(title, type))
            EspressoIdlingResource.decrement()
        }, DUMMY_SERVICE_LATENCY)
        return result
    }
}