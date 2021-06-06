package com.aditya.watchit.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.data.source.local.entity.PopularEntity

@Database(entities = [FavoritEntity::class, FilmEntity::class, PopularEntity::class], exportSchema = false, version = 1)
abstract class SourceDatabase: RoomDatabase() {
    abstract fun favoritDao(): FavoritDao

    companion object {

        @Volatile
        private var INSTANCE: SourceDatabase? = null

        fun getInstance(context: Context): SourceDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    SourceDatabase::class.java,
                    "db_film"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}