package com.aditya.watchit.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.data.source.local.entity.FavoritEntity.Companion.COLUMN_TITLE
import com.aditya.watchit.data.source.local.entity.FavoritEntity.Companion.COLUMN_TYPE
import com.aditya.watchit.data.source.local.entity.FavoritEntity.Companion.TABLE_NAME

@Dao
interface FavoritDao {
    @Query("SELECT * FROM $TABLE_NAME")
    fun getFavorit(): LiveData<List<FavoritEntity>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_TITLE = :title AND $COLUMN_TYPE = :type")
    fun getFilm(title: String, type: String): LiveData<FavoritEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavorit(favoritEntity: FavoritEntity)

    @Delete
    fun deleteFavorit(favoritEntity: FavoritEntity)
}