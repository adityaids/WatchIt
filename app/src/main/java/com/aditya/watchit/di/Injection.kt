package com.aditya.watchit.di

import android.content.Context
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.source.local.LocalDataSource
import com.aditya.watchit.data.source.local.room.SourceDatabase
import com.aditya.watchit.data.source.remote.RemoteDataSource
import com.aditya.watchit.utils.AppExecutor
import com.aditya.watchit.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): DataRepository {

        val database = SourceDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.favoritDao())
        val appExecutor = AppExecutor()

        return DataRepository.getInstance(remoteDataSource, localDataSource, appExecutor)
    }
}