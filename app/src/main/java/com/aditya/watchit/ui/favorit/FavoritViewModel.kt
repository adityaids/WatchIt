package com.aditya.watchit.ui.favorit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.vo.Resource

class FavoritViewModel(private val repository: DataRepository): ViewModel() {

    fun getAllFavorit(): LiveData<PagedList<FavoritEntity>> = repository.getAllFavorit()
    fun deleteFavorit(favoritEntity: FavoritEntity) = repository.deleteFavorit(favoritEntity)
}