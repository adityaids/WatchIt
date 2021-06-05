package com.aditya.watchit.data.source.local

import androidx.lifecycle.LiveData
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.data.source.local.entity.PopularEntity
import com.aditya.watchit.data.source.local.room.FavoritDao

class LocalDataSource private constructor(private val favoritDao: FavoritDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(favoritDao: FavoritDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(favoritDao)
    }

    fun getAllPopular(): LiveData<List<PopularEntity>> = favoritDao.getPopularList()

    fun getFavorit(): LiveData<List<FavoritEntity>> = favoritDao.getFavorit()

    fun getFilmByType(type: String): LiveData<List<FilmEntity>> = favoritDao.getFilmByType(type)

    fun getFilm(title: String, type: String): LiveData<FilmEntity> = favoritDao.getFilm(title, type)

    fun getFilmFavorit(title: String, type: String): LiveData<FavoritEntity> = favoritDao.getFilmFavorit(title, type)

    fun addToFavorit(favoritEntity: FavoritEntity) = favoritDao.addToFavorit(favoritEntity)

    fun insertToPopular(popular: List<PopularEntity>) = favoritDao.insertToPopular(popular)

    fun insertToFilm(film: List<FilmEntity>) = favoritDao.insertToFilm(film)

    fun updateFilm(film: FilmEntity) = favoritDao.updateFilm(film)

    fun deleteFavorit(favoritEntity: FavoritEntity) = favoritDao.deleteFavorit(favoritEntity)
}