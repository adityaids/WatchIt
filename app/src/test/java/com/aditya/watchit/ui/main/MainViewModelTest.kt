package com.aditya.watchit.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.data.source.local.entity.PopularEntity
import com.aditya.watchit.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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

    @Mock
    private lateinit var pagedListFilm: PagedList<FilmEntity>

    @Mock
    private lateinit var pagedListPopular: PagedList<PopularEntity>

    @Before
    fun setUp(){
        viewModel = MainViewModel(dataRepository)
    }

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<FilmEntity>>>

    @Mock
    private lateinit var popularObserver: Observer<Resource<PagedList<PopularEntity>>>

    @Test
    fun testGetMovieList() {
        val dummyMovie = Resource.success(pagedListFilm)
        `when`(dummyMovie.data?.size).thenReturn(5)
        val movie = MutableLiveData<Resource<PagedList<FilmEntity>>>()
        movie.value = dummyMovie

        `when`(dataRepository.getAllMovies("Movies")).thenReturn(movie)
        val movieEntities = viewModel.getMovieList().value?.data
        verify(dataRepository).getAllMovies("Movies")
        assertNotNull(movieEntities)
        assertEquals(5, movieEntities?.size)

        viewModel.getMovieList().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun testGetTvList() {
        val dummyTv = Resource.success(pagedListFilm)
        `when`(dummyTv.data?.size).thenReturn(5)
        val tv = MutableLiveData<Resource<PagedList<FilmEntity>>>()
        tv.value = dummyTv
        `when`(dataRepository.getAllTv("Tv Series")).thenReturn(tv)
        val tvEntities = viewModel.getTvList().value?.data
        verify(dataRepository).getAllTv("Tv Series")
        assertNotNull(tvEntities)
        assertEquals(5, tvEntities?.size)

        viewModel.getTvList().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }

    @Test
    fun testGetPopularList(){
        val dummyPopular = Resource.success(pagedListPopular)
        `when`(dummyPopular.data?.size).thenReturn(5)
        val popular = MutableLiveData<Resource<PagedList<PopularEntity>>>()
        popular.value = dummyPopular

        `when`(dataRepository.getAllPopular()).thenReturn(popular)
        val popularEntities = viewModel.getPopularList().value?.data
        verify(dataRepository).getAllPopular()
        assertNotNull(popularEntities)
        assertEquals(5, popularEntities?.size)

        viewModel.getPopularList().observeForever(popularObserver)
        verify(popularObserver).onChanged(dummyPopular)
    }
}