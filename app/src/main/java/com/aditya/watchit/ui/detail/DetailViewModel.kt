package com.aditya.watchit.ui.detail

import androidx.lifecycle.ViewModel
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.utils.DummyData

internal class DetailViewModel(private val repository: DataRepository): ViewModel() {
    private lateinit var title: String
    private lateinit var type: String

    fun setTitle(name: String, filmType: String){
        this.title = name
        this.type = filmType
    }

    fun getFilm(): FilmModel{
        lateinit var film: FilmModel
        val listMovie = DummyData.generateMovieDummy()
        val listTv = DummyData.generateTvDummy()
        if (type == "Movies") {
            for (filmModel in listMovie) {
                if (filmModel.title == title) {
                    film = filmModel
                }
            }
        } else {
            for (filmModel in listTv) {
                if (filmModel.title == title) {
                    film = filmModel
                }
            }
        }
        return film
    }
}