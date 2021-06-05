package com.aditya.watchit.data

import com.aditya.watchit.data.source.local.entity.FilmEntity

interface OnClickedItem {
    fun onClickedItemCallback(filmEntity: FilmEntity)
}