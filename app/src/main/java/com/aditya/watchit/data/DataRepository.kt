package com.aditya.watchit.data

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

    override fun getAllPopular(): ArrayList<FilmModel> {
        val popularResponses = remoteDataSource.getPopular()
        val filmList = ArrayList<FilmModel>()
        for (response in popularResponses) {
            val film = FilmModel(response.title,
                response.type,
                response.description,
                response.banner)
            filmList.add(film)
        }
        return filmList
    }

    override fun getAllMovies(): ArrayList<FilmModel> {
        val movieResponses = remoteDataSource.getMovieList()
        val filmList = ArrayList<FilmModel>()
        for (response in movieResponses) {
            val film = FilmModel(response.title,
                response.type,
                response.description,
                response.banner)
            filmList.add(film)
        }
        return filmList
    }

    override fun getAllTv(): ArrayList<FilmModel> {
        val tvResponses = remoteDataSource.getTvList()
        val filmList = ArrayList<FilmModel>()
        for (response in tvResponses) {
            val film = FilmModel(response.title,
                response.type,
                response.description,
                response.banner)
            filmList.add(film)
        }
        return filmList
    }
}