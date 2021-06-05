package com.aditya.watchit.data

import androidx.lifecycle.LiveData
import com.aditya.watchit.data.source.local.LocalDataSource
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.data.source.local.entity.PopularEntity
import com.aditya.watchit.data.source.remote.ApiResponse
import com.aditya.watchit.data.source.remote.RemoteDataSource
import com.aditya.watchit.utils.AppExecutor
import com.aditya.watchit.vo.Resource

class DataRepository private constructor(private val remoteDataSource: RemoteDataSource,
                                         private val localDataSource: LocalDataSource,
                                         private val appExecutor: AppExecutor) : FilmDataSource {

    companion object {
        @Volatile
        private var instance: DataRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localDataSource: LocalDataSource, appExecutor: AppExecutor): DataRepository =
                instance ?: synchronized(this) {
                    instance ?: DataRepository(remoteData, localDataSource, appExecutor).apply { instance = this }
                }
    }

    override fun getAllPopular(): LiveData<Resource<List<PopularEntity>>> {
        return object : NetworkBoundResource<List<PopularEntity>, List<FilmModel>>(appExecutor){
            override fun loadFromDB(): LiveData<List<PopularEntity>> =
                localDataSource.getAllPopular()

            override fun shouldFetch(data: List<PopularEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<FilmModel>>> =
                remoteDataSource.getPopular()

            override fun saveCallResult(data: List<FilmModel>) {
                val popularList = ArrayList<PopularEntity>()
                for (response in data) {
                    val filmPopular = PopularEntity(response.title,
                        response.type,
                        response.description,
                        response.banner)
                    popularList.add(filmPopular)
                }
                localDataSource.insertToPopular(popularList)
            }
        }.asLiveData()
    }

    override fun getAllMovies(type: String): LiveData<Resource<List<FilmEntity>>> {
        return object : NetworkBoundResource<List<FilmEntity>, List<FilmModel>>(appExecutor){
            override fun loadFromDB(): LiveData<List<FilmEntity>> =
                localDataSource.getFilmByType(type)


            override fun shouldFetch(data: List<FilmEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<FilmModel>>> =
                remoteDataSource.getMovieList()


            override fun saveCallResult(data: List<FilmModel>) {
                val movieList = ArrayList<FilmEntity>()
                for (response in data) {
                    val movie = FilmEntity(response.title,
                        response.type,
                        response.description,
                        response.banner)
                    movieList.add(movie)
                }
                localDataSource.insertToFilm(movieList)
            }

        }.asLiveData()
    }

    override fun getAllTv(type: String): LiveData<Resource<List<FilmEntity>>> {
        return object : NetworkBoundResource<List<FilmEntity>, List<FilmModel>>(appExecutor){
            override fun loadFromDB(): LiveData<List<FilmEntity>> =
                localDataSource.getFilmByType(type)


            override fun shouldFetch(data: List<FilmEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<FilmModel>>> =
                remoteDataSource.getMovieList()


            override fun saveCallResult(data: List<FilmModel>) {
                val movieList = ArrayList<FilmEntity>()
                for (response in data) {
                    val movie = FilmEntity(response.title,
                        response.type,
                        response.description,
                        response.banner)
                    movieList.add(movie)
                }
                localDataSource.insertToFilm(movieList)
            }

        }.asLiveData()
    }

    override fun getFilm(title: String, type: String): LiveData<Resource<FilmEntity>> {
        return object : NetworkBoundResource<FilmEntity, FilmModel>(appExecutor){
            override fun loadFromDB(): LiveData<FilmEntity>  =
                localDataSource.getFilm(title, type)

            override fun shouldFetch(data: FilmEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<FilmModel>> =
                remoteDataSource.getFilm(title, type)

            override fun saveCallResult(data: FilmModel) {
                val movie = FilmEntity(data.title,
                    data.type,
                    data.description,
                    data.banner)
                localDataSource.updateFilm(movie)
            }
        }.asLiveData()
    }

    override fun getAllFavorit(favoritEntity: FavoritEntity): LiveData<List<FavoritEntity>> =
        localDataSource.getFavorit()

    override fun setFavorit(favoritEntity: FavoritEntity) =
        appExecutor.diskIO().execute{localDataSource.addToFavorit(favoritEntity)}

    override fun deleteFavorit(favoritEntity: FavoritEntity) =
        localDataSource.deleteFavorit(favoritEntity)
}