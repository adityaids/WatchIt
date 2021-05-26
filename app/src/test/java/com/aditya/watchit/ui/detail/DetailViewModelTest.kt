package com.aditya.watchit.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.utils.DummyData
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DummyData.generateMovieDummy()[0]
    private val dummyTv = DummyData.generateTvDummy()[0]

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        /*
        viewModel = DetailViewModel()
        viewModel.setFilm(dummyMovie)
        viewModel.setFilm(dummyTv)

         */
    }

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<FilmModel>


    @Test
    fun getDetailMovie() {
        val dummyMovie = DummyData.generateMovieDummy()
        val movie = MutableLiveData<List<FilmModel>>()
        movie.value = dummyMovie
        /*
        `when`(dataRepository.getAllMovies()).thenReturn(course)
        val courseEntity = viewModel.getCourse().value as CourseEntity
        verify(academyRepository).getCourseWithModules(courseId)
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId, courseEntity.courseId)
        assertEquals(dummyCourse.deadline, courseEntity.deadline)
        assertEquals(dummyCourse.description, courseEntity.description)
        assertEquals(dummyCourse.imagePath, courseEntity.imagePath)
        assertEquals(dummyCourse.title, courseEntity.title)

        viewModel.getCourse().observeForever(courseObserver)
        verify(courseObserver).onChanged(dummyCourse)

         */
    }

    @Test
    fun getDetailTv(){
        /*
        viewModel.setFilm(dummyTv)
        val tv = viewModel.getFilm()
        Assert.assertNotNull(tv)
        assertEquals(dummyTv.title, tv.title)
        assertEquals(dummyTv.type, tv.type)
        assertEquals(dummyTv.description, tv.description)
        assertEquals(dummyTv.banner, tv.banner)
         */
    }
}