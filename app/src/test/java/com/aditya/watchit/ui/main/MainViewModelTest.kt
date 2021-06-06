package com.aditya.watchit.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.data.source.local.entity.PopularEntity
import com.aditya.watchit.utils.DummyData
import com.aditya.watchit.vo.Resource
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
    private lateinit var observer: Observer<Resource<List<FilmEntity>>>

    @Mock
    private lateinit var popularObserver: Observer<Resource<List<PopularEntity>>>

    @Test
    fun testGetMovieList() {
        val dummyMovie = Resource.success(DummyData.generateMovieDummy())
        val movie = MutableLiveData<Resource<List<FilmEntity>>>()
        val dummyList = ArrayList<FilmEntity>()
        for (film in dummyMovie.data!!){
            val mFilm = FilmEntity(
                film.title,
                film.type,
                film.description,
                film.banner
            )
            dummyList.add(mFilm)
        }
        val dummyFilm = Resource.success(dummyList.toList())
        movie.value = dummyFilm

        `when`(dataRepository.getAllMovies("Movies")).thenReturn(movie)
        val movieEntities = viewModel.getMovieList().value?.data
        verify(dataRepository).getAllMovies("Movies")
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getMovieList().observeForever(observer)
        verify(observer).onChanged(dummyFilm)
    }

    @Test
    fun testGetTvList() {
        val dummyTv = Resource.success(DummyData.generateTvDummy())
        val tv = MutableLiveData<Resource<List<FilmEntity>>>()
        val dummyList = ArrayList<FilmEntity>()
        for (film in dummyTv.data!!){
            val mFilm = FilmEntity(
                film.title,
                film.type,
                film.description,
                film.banner
            )
            dummyList.add(mFilm)
        }
        val dummyFilm = Resource.success(dummyList.toList())
        tv.value = dummyFilm
        `when`(dataRepository.getAllTv("Tv Series")).thenReturn(tv)
        val tvEntities = viewModel.getTvList().value?.data
        verify(dataRepository).getAllTv("Tv Series")
        assertNotNull(tvEntities)
        assertEquals(10, tvEntities?.size)

        viewModel.getTvList().observeForever(observer)
        verify(observer).onChanged(dummyFilm)
    }

    @Test
    fun testGetPopularList(){
        val dummyPopular = Resource.success(DummyData.generatePopularDummy())
        val popular = MutableLiveData<Resource<List<PopularEntity>>>()
        val dummyList = ArrayList<PopularEntity>()
        for (film in dummyPopular.data!!){
            val mFilm = PopularEntity(
                film.title,
                film.type,
                film.description,
                film.banner
            )
            dummyList.add(mFilm)
        }
        val dummyFilm = Resource.success(dummyList.toList())
        popular.value = dummyFilm

        `when`(dataRepository.getAllPopular()).thenReturn(popular)
        val popularEntities = viewModel.getPopularList().value?.data
        verify(dataRepository).getAllPopular()
        assertNotNull(popularEntities)
        assertEquals(10, popularEntities?.size)

        viewModel.getPopularList().observeForever(popularObserver)
        verify(popularObserver).onChanged(dummyFilm)
    }
}