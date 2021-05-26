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
        try {
            val responseObject = JSONObject(parsingFileToString("ResponsePopular.json").toString())
            val listArray = responseObject.getJSONArray("Popular")
            for (i in 0 until listArray.length()) {
                val film = listArray.getJSONObject(i)

                val title = film.getString("title")
                val type = film.getString("type")
                val description = film.getString("description")
                val image = film.getString("image")

                val filmModel = FilmModel(title, type, description, image)
                filmList.add(filmModel)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return filmList
    }

    fun loadMovies(): List<FilmModel>{
        val filmList = ArrayList<FilmModel>()
        try {
            val responseObject = JSONObject(parsingFileToString("ResponseMovie.json").toString())
            val listArray = responseObject.getJSONArray("Movies")
            for (i in 0 until listArray.length()) {
                val film = listArray.getJSONObject(i)

                val title = film.getString("title")
                val type = film.getString("type")
                val description = film.getString("description")
                val image = film.getString("image")

                val filmModel = FilmModel(title, type, description, image)
                filmList.add(filmModel)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return filmList
    }

    fun loadTv(): List<FilmModel>{
        val filmList = ArrayList<FilmModel>()
        try {
            val responseObject = JSONObject(parsingFileToString("ResponseTv.json").toString())
            val listArray = responseObject.getJSONArray("Tv")
            for (i in 0 until listArray.length()) {
                val film = listArray.getJSONObject(i)

                val title = film.getString("title")
                val type = film.getString("type")
                val description = film.getString("description")
                val image = film.getString("image")

                val filmModel = FilmModel(title, type, description, image)
                filmList.add(filmModel)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return filmList
    }
    fun loadFilm(title: String, type: String): FilmModel{
        lateinit var film: FilmModel
        if (type == "Movies") {
            val listMovie = loadMovies()
            for (filmModel in listMovie) {
                if (filmModel.title == title) {
                    film = filmModel
                }
            }
        } else {
            val listTv = loadTv()
            for (filmModel in listTv) {
                if (filmModel.title == title) {
                    film = filmModel
                }
            }
        }
        return film
    }
}