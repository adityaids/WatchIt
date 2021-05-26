package com.aditya.watchit.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.utils.DummyData
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
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DummyData.generateMovieDummy()[0]
    private val dummyTv = DummyData.generateTvDummy()[0]
    private val titleMovie = dummyMovie.title
    private  val typeMovie = dummyMovie.type
    private val titleTv = dummyTv.title
    private val typeTv = dummyTv.type

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Before
    fun setUp() {
        viewModel = DetailViewModel(dataRepository)
    }

    @Mock
    private lateinit var observer: Observer<FilmModel>


    @Test
    fun getDetailMovie() {
        val film = MutableLiveData<FilmModel>()
        film.value = dummyMovie
        viewModel.setFilm(titleMovie, typeMovie)
        `when`(dataRepository.getFilm(titleMovie, typeMovie)).thenReturn(film)
        val movieItem = viewModel.getFilm().value as FilmModel
        verify(dataRepository).getFilm(titleMovie, typeMovie)
        assertNotNull(movieItem)
        assertEquals(dummyMovie.title, movieItem.title)
        assertEquals(dummyMovie.type, movieItem.type)
        assertEquals(dummyMovie.description, movieItem.description)
        assertEquals(dummyMovie.banner, movieItem.banner)

        viewModel.getFilm().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getDetailTv(){
        val film = MutableLiveData<FilmModel>()
        film.value = dummyTv
        viewModel.setFilm(titleTv, typeTv)
        `when`(dataRepository.getFilm(titleTv, typeTv)).thenReturn(film)
        val tvItem = viewModel.getFilm().value as FilmModel
        verify(dataRepository).getFilm(titleTv, typeTv)
        assertNotNull(tvItem)
        assertEquals(dummyTv.title, tvItem.title)
        assertEquals(dummyTv.type, tvItem.type)
        assertEquals(dummyTv.description, tvItem.description)
        assertEquals(dummyTv.banner, tvItem.banner)

        viewModel.getFilm().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}