package com.aditya.watchit.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.vo.Resource

internal class DetailViewModel(private val repository: DataRepository): ViewModel() {
    private lateinit var title: String
    private lateinit var type: String

    fun setFilm(title: String, type: String){
        this.title = title
        this.type = type
    }

    fun getFilm(): LiveData<Resource<FilmEntity>>{
        return repository.getFilm(title, type)
    }

    fun addToFavorit(filmEntity: FilmEntity){
        val result = FavoritEntity(
            filmEntity.title,
            filmEntity.type,
            filmEntity.description,
            filmEntity.banner,
                true
        )
        repository.setFavorit(result)
    }
}