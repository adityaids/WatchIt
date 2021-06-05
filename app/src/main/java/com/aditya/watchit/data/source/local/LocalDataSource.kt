package com.aditya.watchit.data.source.local

import androidx.lifecycle.LiveData
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.data.source.local.room.FavoritDao

class LocalDataSource private constructor(private val favoritDao: FavoritDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(favoritDao: FavoritDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(favoritDao)
    }

    fun getFavorit(): LiveData<List<FavoritEntity>> = favoritDao.getFavorit()

    fun getFilm(title: String, type: String): LiveData<FavoritEntity> = favoritDao.getFilm(title, type)

    fun addToFavorit(favoritEntity: FavoritEntity) = favoritDao.addToFavorit(favoritEntity)

    fun deleteFavorit(favoritEntity: FavoritEntity) = favoritDao.deleteFavorit(favoritEntity)
}