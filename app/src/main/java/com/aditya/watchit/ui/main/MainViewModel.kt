package com.aditya.watchit.ui.main

import androidx.lifecycle.ViewModel
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.utils.DummyData

class MainViewModel: ViewModel() {
    fun getMovieList(): List<FilmModel> = DummyData.generateMovieDummy()
    fun getTvList(): List<FilmModel> = DummyData.generateTvDummy()
}