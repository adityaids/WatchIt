package com.aditya.watchit.ui.detail

import com.aditya.watchit.utils.DummyData
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DummyData.generateMovieDummy()[0]
    private val titleMovie = dummyMovie.title
    private val typeMovie = dummyMovie.type
    private val dummyTv = DummyData.generateTvDummy()[0]
    private val titleTv = dummyTv.title
    private val typeTv = dummyTv.type


    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setTitle(titleMovie, typeMovie)
        viewModel.setTitle(titleTv, typeTv)
    }

    @Test
    fun getDetailMovie() {
        viewModel.setTitle(dummyMovie.title, dummyMovie.type)
        val film = viewModel.getFilm()
        Assert.assertNotNull(film)
        assertEquals(dummyMovie.title, film.title)
        assertEquals(dummyMovie.type, film.type)
        assertEquals(dummyMovie.description, film.description)
        assertEquals(dummyMovie.banner, film.banner)
    }

    @Test
    fun getDetailTv(){
        viewModel.setTitle(dummyTv.title, dummyTv.type)
        val tv = viewModel.getFilm()
        Assert.assertNotNull(tv)
        assertEquals(dummyTv.title, tv.title)
        assertEquals(dummyTv.type, tv.type)
        assertEquals(dummyTv.description, tv.description)
        assertEquals(dummyTv.banner, tv.banner)
    }
}