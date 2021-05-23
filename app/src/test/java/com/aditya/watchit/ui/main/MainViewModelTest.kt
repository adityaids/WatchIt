package com.aditya.watchit.ui.main

import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

class MainViewModelTest{
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp(){
        viewModel = MainViewModel()
    }

    @Test
    fun testGetMovieList() {
        val movielist = viewModel.getMovieList()
        assertNotNull(movielist)
        assertEquals(10, movielist.size)
    }

    @Test
    fun testGetTvList() {
        val tvList = viewModel.getTvList()
        assertNotNull(tvList)
        assertEquals(10, tvList.size)
    }
}