package com.aditya.watchit.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.utils.DummyData
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest{
    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Before
    fun setUp(){
        viewModel = MainViewModel(dataRepository)
    }

    @Mock
    private lateinit var observer: Observer<List<FilmModel>>

    @Test
    fun testGetMovieList() {
        val dummyMovie = DummyData.generateMovieDummy()
        val movie = MutableLiveData<List<FilmModel>>()
        movie.value = dummyMovie

        `when`(dataRepository.getAllMovies()).thenReturn(movie)
        val movieEntities = viewModel.getMovieList().value
        verify(dataRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getMovieList().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun testGetTvList() {
        val dummyTv = DummyData.generateTvDummy()
        val tv = MutableLiveData<List<FilmModel>>()
        tv.value = dummyTv

        `when`(dataRepository.getAllTv()).thenReturn(tv)
        val tvEntities = viewModel.getTvList().value
        verify(dataRepository).getAllTv()
        assertNotNull(tvEntities)
        assertEquals(10, tvEntities?.size)

        viewModel.getTvList().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }

    @Test
    fun testGetPopularList(){
        val dummyPopular = DummyData.generatePopularDummy()
        val film = MutableLiveData<List<FilmModel>>()
        film.value = dummyPopular

        `when`(dataRepository.getAllPopular()).thenReturn(film)
        val popularEntities = viewModel.getPopularList().value
        verify(dataRepository).getAllPopular()
        assertNotNull(popularEntities)
        assertEquals(10, popularEntities?.size)

        viewModel.getPopularList().observeForever(observer)
        verify(observer).onChanged(dummyPopular)
    }
}