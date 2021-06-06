package com.aditya.watchit.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.data.source.local.entity.PopularEntity

@Dao
interface FavoritDao {


    @Query("SELECT * FROM ${PopularEntity.TABLE_NAME}")
    fun getPopularList(): DataSource.Factory<Int, PopularEntity>

    @Query("SELECT * FROM ${FavoritEntity.TABLE_NAME}")
    fun getFavorit(): DataSource.Factory<Int, FavoritEntity>

    @Query("SELECT * FROM ${FilmEntity.TABLE_NAME} WHERE ${FilmEntity.COLUMN_TYPE} = :type")
    fun getFilmByType(type: String): DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM ${FilmEntity.TABLE_NAME} WHERE ${FilmEntity.COLUMN_TITLE} = :title AND ${FilmEntity.COLUMN_TYPE} = :type")
    fun getFilm(title: String, type: String): LiveData<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavorit(favoritEntity: FavoritEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToPopular(popular: List<PopularEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertToFilm(film: List<FilmEntity>)

    @Update
    fun updateFilm(filmEntity: FilmEntity)

    @Delete
    fun deleteFavorit(favoritEntity: FavoritEntity)
}