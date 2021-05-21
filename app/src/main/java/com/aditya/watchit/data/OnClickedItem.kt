package com.aditya.watchit.data

import android.view.View

interface OnClickedItem {
    fun onClickedItemCallback(filmModel: FilmModel, imageView: View)
}