package com.aditya.watchit.ui.main

import androidx.lifecycle.ViewModel
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.utils.DummyData

class MainViewModel (private val repository: DataRepository): ViewModel() {
    fun getMovieList(): List<FilmModel> = DummyData.generateMovieDummy()
    fun getTvList(): List<FilmModel> = DummyData.generateTvDummy()
}