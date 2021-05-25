package com.aditya.watchit.di

import android.content.Context
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.source.remote.RemoteDataSource
import com.aditya.watchit.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): DataRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return DataRepository.getInstance(remoteDataSource)
    }
}