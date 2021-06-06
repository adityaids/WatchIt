package com.aditya.watchit.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.utils.DummyData
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
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = Resource.success(DummyData.generateMovieDummy()[0])
    private val dummyTv = Resource.success(DummyData.generateTvDummy()[0])
    private val titleMovie = dummyMovie.data?.title
    private  val typeMovie = dummyMovie.data?.type
    private val titleTv = dummyTv.data?.title
    private val typeTv = dummyTv.data?.type

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Before
    fun setUp() {
        viewModel = DetailViewModel(dataRepository)
    }

    @Mock
    private lateinit var observer: Observer<Resource<FilmEntity>>

    @Test
    fun getDetailMovie() {
        val film = MutableLiveData<Resource<FilmEntity>>()
        val mFilm = FilmEntity(dummyMovie.data?.title?:"null",
            dummyMovie.data?.type?:"null",
            dummyMovie.data?.description?:"null",
            dummyMovie.data?.banner?:"null")
        val dummyFilm = Resource.success(mFilm)
        film.value = dummyFilm
        viewModel.setFilm(titleMovie?:"null", typeMovie?:"null")
        `when`(dataRepository.getFilm(titleMovie?:"null", typeMovie?:"null")).thenReturn(film)
        val movieItem = viewModel.getFilm().value?.data as FilmEntity
        verify(dataRepository).getFilm(titleMovie?:"null", typeMovie?:"null")
        assertNotNull(movieItem)
        assertEquals(dummyMovie.data?.title, movieItem.title)
        assertEquals(dummyMovie.data?.type, movieItem.type)
        assertEquals(dummyMovie.data?.description, movieItem.description)
        assertEquals(dummyMovie.data?.banner, movieItem.banner)

        viewModel.getFilm().observeForever(observer)
        verify(observer).onChanged(dummyFilm)
    }

    @Test
    fun getDetailTv(){
        val film = MutableLiveData<Resource<FilmEntity>>()
        val mFilm = FilmEntity(dummyTv.data?.title?:"null",
            dummyTv.data?.type?:"null",
            dummyTv.data?.description?:"null",
            dummyTv.data?.banner?:"null")
        val dummyFilm = Resource.success(mFilm)
        film.value = dummyFilm
        viewModel.setFilm(titleTv?:"null", typeTv?:"null")
        `when`(dataRepository.getFilm(titleTv?:"null", typeTv?:"null")).thenReturn(film)
        val tvItem = viewModel.getFilm().value?.data as FilmEntity
        verify(dataRepository).getFilm(titleTv?:"null", typeTv?:"null")
        assertNotNull(tvItem)
        assertEquals(dummyTv.data?.title, tvItem.title)
        assertEquals(dummyTv.data?.type, tvItem.type)
        assertEquals(dummyTv.data?.description, tvItem.description)
        assertEquals(dummyTv.data?.banner, tvItem.banner)

        viewModel.getFilm().observeForever(observer)
        verify(observer).onChanged(dummyFilm)
    }

    @Test
    fun addToFavorit(){

    }
}