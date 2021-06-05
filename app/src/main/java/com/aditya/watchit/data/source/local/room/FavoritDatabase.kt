package com.aditya.watchit.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aditya.watchit.data.source.local.entity.FavoritEntity

@Database(entities = [FavoritEntity::class], exportSchema = false, version = 1)
abstract class FavoritDatabase: RoomDatabase() {
    abstract fun favoritDao(): FavoritDao

    companion object {

        @Volatile
        private var INSTANCE: FavoritDatabase? = null

        fun getInstance(context: Context): FavoritDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    FavoritDatabase::class.java,
                    "favorit"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}