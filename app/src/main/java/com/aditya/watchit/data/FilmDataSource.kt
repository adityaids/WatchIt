package com.aditya.watchit.data

import androidx.lifecycle.LiveData
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.data.source.local.entity.PopularEntity
import com.aditya.watchit.vo.Resource

interface FilmDataSource {
    fun getAllPopular(): LiveData<Resource<List<PopularEntity>>>
    fun getAllMovies(type: String): LiveData<Resource<List<FilmEntity>>>
    fun getAllTv(type: String): LiveData<Resource<List<FilmEntity>>>
    fun getFilm(title: String, type: String): LiveData<Resource<FilmEntity>>
    fun getAllFavorit(favoritEntity: FavoritEntity): LiveData<List<FavoritEntity>>
    fun setFavorit(favoritEntity: FavoritEntity)
    fun deleteFavorit(favoritEntity: FavoritEntity)
}