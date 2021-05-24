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


    override fun getAllMovies() {
        TODO("Not yet implemented")
    }

    override fun getAllTv() {
        TODO("Not yet implemented")
    }
}