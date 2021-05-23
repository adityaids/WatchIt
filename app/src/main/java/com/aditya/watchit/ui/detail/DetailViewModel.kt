package com.aditya.watchit.ui.detail

import androidx.lifecycle.ViewModel
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.utils.DummyData

class DetailViewModel: ViewModel() {
    private lateinit var title: String

    fun setTitle(name: String){
        this.title = name
    }

    fun getFilm(): FilmModel{
        lateinit var film: FilmModel
        val listMovie = DummyData.generateMovieDummy()
        val listTv = DummyData.generateTvDummy()
        if (title != "-") {
            for (filmModel in listMovie) {
                if (filmModel.title == title) {
                    film = filmModel
                }
            }
            for (filmModel in listTv) {
                if (filmModel.title == title) {
                    film = filmModel
                }
            }
        }
        return film
    }
}