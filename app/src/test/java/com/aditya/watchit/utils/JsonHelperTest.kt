package com.aditya.watchit.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class JsonHelperTest {
    private lateinit var jsonHelper: JsonHelper

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        jsonHelper = JsonHelper(context)
    }

    @Test
    fun loadMovies_returnsData() {
        val movies = jsonHelper.loadMovies()
        assertNotNull(movies)
        assertEquals(10, movies.size)
        assertEquals("John Wick Chapter 3 - Parabellum", movies[0].title)
    }

    @Test
    fun loadFilm_validTitle() {
        val film = jsonHelper.loadFilm("John Wick Chapter 3 - Parabellum", "Movies")
        assertNotNull(film)
        assertEquals("John Wick Chapter 3 - Parabellum", film.title)
    }

    @Test
    fun loadFilm_invalidTitle_throwsException() {
        assertThrows(UninitializedPropertyAccessException::class.java) {
            jsonHelper.loadFilm("Unknown", "Movies")
        }
    }
}
