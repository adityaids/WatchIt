package com.aditya.watchit.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class FilmModel (
    var title: String,
    var type: String,
    var description: String,
    var banner: Int
    ): Parcelable