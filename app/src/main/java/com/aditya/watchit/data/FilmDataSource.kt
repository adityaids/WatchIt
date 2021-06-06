package com.aditya.watchit.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.data.source.local.entity.PopularEntity
import com.aditya.watchit.vo.Resource

interface FilmDataSource {
    fun getAllPopular(): LiveData<Resource<PagedList<PopularEntity>>>
    fun getAllMovies(type: String): LiveData<Resource<PagedList<FilmEntity>>>
    fun getAllTv(type: String): LiveData<Resource<PagedList<FilmEntity>>>
    fun getFilm(title: String, type: String): LiveData<Resource<FilmEntity>>
    fun getAllFavorit(): LiveData<PagedList<FavoritEntity>>
    fun setFavorit(favoritEntity: FavoritEntity)
    fun deleteFavorit(favoritEntity: FavoritEntity)
}