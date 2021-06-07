package com.aditya.watchit.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.aditya.watchit.data.source.local.LocalDataSource
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.data.source.local.entity.PopularEntity
import com.aditya.watchit.data.source.remote.RemoteDataSource
import com.aditya.watchit.utils.AppExecutor
import com.aditya.watchit.utils.DummyData
import com.aditya.watchit.utils.LiveDataTestUtil
import com.aditya.watchit.utils.PagedListUtil
import com.aditya.watchit.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class DataRepositoryTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutor::class.java)
    private val dataRepository = FakeDataRepository(remote, local, appExecutors)

    private val popularResponses = DummyData.generatePopularDummy()
    private val movieResponses = DummyData.generateMovieDummy()
    private val tvResponses = DummyData.generateTvDummy()
    private val filmMovie = DummyData.generateMovieDummy()[0]

    @Test
    fun getAllPopular() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, PopularEntity>
        `when`(local.getAllPopular()).thenReturn(dataSourceFactory)
        dataRepository.getAllPopular()
        val popularEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generatePopularDummy()))
        verify(local).getAllPopular()
        assertNotNull(popularEntities)
        assertEquals(popularResponses.size.toLong(), popularEntities.data?.size?.toLong())
    }

    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        `when`(local.getFilmByType("Movies")).thenReturn(dataSourceFactory)
        dataRepository.getAllMovies("Movies")
        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateMovieDummy()))
        verify(local).getFilmByType("Movies")
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTv() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        `when`(local.getFilmByType("Tv Series")).thenReturn(dataSourceFactory)
        dataRepository.getAllMovies("Tv Series")
        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateTvDummy()))
        verify(local).getFilmByType("Tv Series")
        assertNotNull(tvEntities)
        assertEquals(tvResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getFilm() {
        val dummyMovie = DummyData.generateMovieDummy()[0]
        val movie = MutableLiveData<Resource<FilmEntity>>()
        val mFilm = FilmEntity(dummyMovie.title,
            dummyMovie.type,
            dummyMovie.description,
            dummyMovie.banner)
        val dummyFilm = Resource.success(mFilm)
        movie.value = dummyFilm
        `when`(dataRepository.getFilm(filmMovie.title, filmMovie.type)).thenReturn(movie)
        val film = Resource.success(dataRepository.getFilm(filmMovie.title, filmMovie.type))
        verify(local).getFilm(filmMovie.title, filmMovie.type)
        assertNotNull(film)
        assertEquals(filmMovie.title, movie.value?.data?.title)
    }
}