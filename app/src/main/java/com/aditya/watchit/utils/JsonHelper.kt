package com.aditya.watchit.utils

import android.content.Context
import com.aditya.watchit.data.FilmModel
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadPopular(): List<FilmModel>{
        val filmList = ArrayList<FilmModel>()
        return filmList
    }

    fun loadMovies(): List<FilmModel>{
        val filmList = ArrayList<FilmModel>()
        return filmList
    }

    fun loadTv(): List<FilmModel>{
        val filmList = ArrayList<FilmModel>()
        return filmList
    }
}