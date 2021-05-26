package com.aditya.watchit.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aditya.watchit.data.source.remote.RemoteDataSource
import com.aditya.watchit.utils.DummyData
import com.aditya.watchit.utils.LiveDataTestUtil
import org.junit.Rule
import org.junit.Test
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import org.mockito.Mockito.mock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

class DataRepositoryTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = mock(RemoteDataSource::class.java)
    private val dataRepository = FakeDataRepository(remote)

    private val popularResponses = DummyData.generatePopularDummy()
    private val movieResponses = DummyData.generateMovieDummy()
    private val tvResponses = DummyData.generateTvDummy()
    private val filmMovie = DummyData.generateMovieDummy()[0]
    private val filmTitle = filmMovie.title
    private val filmType = filmMovie.type

    @Test
    fun getAllPopular() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadPopularCallback)
                .onAllPopularReceived(popularResponses)
            null
        }.`when`(remote).getPopular(any())
        val popularEntities = LiveDataTestUtil.getValue(dataRepository.getAllPopular())
        verify(remote).getPopular(any())
        assertNotNull(popularEntities)
        assertEquals(popularResponses.size.toLong(), popularEntities.size.toLong())
    }

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieCallback)
                .onAllMovieReceived(movieResponses)
            null
        }.`when`(remote).getMovieList(any())
        val movieEntities = LiveDataTestUtil.getValue(dataRepository.getAllMovies())
        verify(remote).getMovieList(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getAllTv() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvCallback)
                .onAllTvReceived(tvResponses)
            null
        }.`when`(remote).getTvList(any())
        val tvEntities = LiveDataTestUtil.getValue(dataRepository.getAllTv())
        verify(remote).getTvList(any())
        assertNotNull(tvEntities)
        assertEquals(tvResponses.size.toLong(), tvEntities.size.toLong())
    }

    @Test
    fun getFilm() {
        doAnswer { invocation ->
            (invocation.arguments[2] as RemoteDataSource.LoadFilmCallback)
                .onFilmReceived(filmMovie)
            null
        }.`when`(remote).getFilm(eq(filmTitle), eq(filmType), any())
        val film = LiveDataTestUtil.getValue(dataRepository.getFilm(filmMovie.title, filmMovie.type))
        verify(remote).getFilm(eq(filmTitle), eq(filmType), any())
        assertNotNull(film)
        assertEquals(filmMovie.title, film.title)
    }
}