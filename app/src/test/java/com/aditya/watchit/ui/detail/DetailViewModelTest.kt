package com.aditya.watchit.ui.detail

import com.aditya.watchit.utils.DummyData
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyFilm = DummyData.generateMovieDummy()[0]
    private val title = dummyFilm.title
    private val type = dummyFilm.type
    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setTitle(title, type)
    }

    @Test
    fun getFilm() {
        viewModel.setTitle(dummyFilm.title, dummyFilm.type)
        val film = viewModel.getFilm()
        Assert.assertNotNull(film)
        assertEquals(dummyFilm.title, film.title)
        assertEquals(dummyFilm.type, film.type)
        assertEquals(dummyFilm.description, film.description)
        assertEquals(dummyFilm.banner, film.banner)
    }
}