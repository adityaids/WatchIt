package com.aditya.watchit.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.FilmModel

internal class DetailViewModel(private val repository: DataRepository): ViewModel() {
    private lateinit var title: String
    private lateinit var type: String

    fun setFilm(title: String, type: String){
        this.title = title
        this.type = type
    }

    fun getFilm(): LiveData<FilmModel>{
        return repository.getFilm(title, type)
    }
}