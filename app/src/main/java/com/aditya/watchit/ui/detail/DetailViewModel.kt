package com.aditya.watchit.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.utils.DummyData

internal class DetailViewModel: ViewModel() {
    private val film = MutableLiveData<FilmModel>()

    fun setFilm(filmModel: FilmModel){
        film.postValue(filmModel)
    }

    fun getFilm(): LiveData<FilmModel>{
        return film
    }
}