package com.aditya.watchit.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aditya.watchit.data.source.remote.RemoteDataSource

class DataRepository private constructor(private val remoteDataSource: RemoteDataSource) : FilmDataSource {

    companion object {
        @Volatile
        private var instance: DataRepository? = null
        fun getInstance(remoteData: RemoteDataSource): DataRepository =
                instance ?: synchronized(this) {
                    instance ?: DataRepository(remoteData).apply { instance = this }
                }
    }

    override fun getAllPopular(): LiveData<List<FilmModel>> {
        val popularResult = MutableLiveData<List<FilmModel>>()
        remoteDataSource.getPopular(object : RemoteDataSource.LoadPopularCallback {
            override fun onAllPopularReceived(popularResponse: List<FilmModel>) {
                val filmList = ArrayList<FilmModel>()
                for (response in popularResponse) {
                    val film = FilmModel(
                        response.title,
                        response.type,
                        response.description,
                        response.banner
                    )
                    filmList.add(film)
                }
                popularResult.postValue(filmList)
            }
        })
        return popularResult
    }

    override fun getAllMovies(): LiveData<List<FilmModel>> {
        val movieResults = MutableLiveData<List<FilmModel>>()
        remoteDataSource.getMovieList(object : RemoteDataSource.LoadMovieCallback{
            override fun onAllMovieReceived(movieResponses: List<FilmModel>) {
                val filmList = ArrayList<FilmModel>()
                for (response in movieResponses) {
                    val film = FilmModel(response.title,
                        response.type,
                        response.description,
                        response.banner)
                    filmList.add(film)
                }
                movieResults.postValue(filmList)
            }
        })
        return movieResults
    }

    override fun getAllTv(): LiveData<List<FilmModel>> {
        val tvResults = MutableLiveData<List<FilmModel>>()
        remoteDataSource.getTvList(object : RemoteDataSource.LoadTvCallback{
            override fun onAllTvReceived(tvResponse: List<FilmModel>) {
                val filmList = ArrayList<FilmModel>()
                for (response in tvResponse) {
                    val film = FilmModel(
                        response.title,
                        response.type,
                        response.description,
                        response.banner
                    )
                    filmList.add(film)
                }
                tvResults.postValue(filmList)
            }
        })
        return tvResults
    }

    override fun getFilm(title: String, type: String): LiveData<FilmModel> {
        val result = MutableLiveData<FilmModel>()
        remoteDataSource.getFilm(title, type, object : RemoteDataSource.LoadFilmCallback{
            override fun onFilmReceived(filmModel: FilmModel) {
                result.postValue(filmModel)
            }
        })
        return result
    }
}