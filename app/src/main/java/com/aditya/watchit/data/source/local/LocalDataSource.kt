package com.aditya.watchit.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
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

    fun getAllPopular(): DataSource.Factory<Int, PopularEntity> = favoritDao.getPopularList()

    fun getFavorit(): DataSource.Factory<Int, FavoritEntity> = favoritDao.getFavorit()

    fun getFilmByType(type: String): DataSource.Factory<Int, FilmEntity> = favoritDao.getFilmByType(type)

    fun getFilm(title: String, type: String): LiveData<FilmEntity> = favoritDao.getFilm(title, type)

    fun addToFavorit(favoritEntity: FavoritEntity) = favoritDao.addToFavorit(favoritEntity)

    fun insertToPopular(popular: List<PopularEntity>) = favoritDao.insertToPopular(popular)

    fun insertToFilm(film: List<FilmEntity>) = favoritDao.insertToFilm(film)

    fun updateFilm(film: FilmEntity) = favoritDao.updateFilm(film)

    fun deleteFavorit(favoritEntity: FavoritEntity) = favoritDao.deleteFavorit(favoritEntity)
}