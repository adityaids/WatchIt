package com.aditya.watchit.data

import com.aditya.watchit.data.source.remote.RemoteDataSource
import com.aditya.watchit.utils.DummyData
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import junit.framework.TestCase
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class DataRepositoryTest{
/*
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val dataRepository = FakeDataRepository(remote)

    private val popularResponses = DummyData.generatePopularDummy()
    private val movieResponses = DummyData.generateMovieDummy()
    private val tvResponses = DummyData.generateTvDummy()

    @Test
    fun getAllCourses() {
        `when`<List<FilmModel>>(remote.getPopular()).thenReturn(popularResponses)
        val popularEntities = dataRepository.getAllPopular()
        verify<RemoteDataSource>(remote).getPopular()
        assertNotNull(popularEntities)
        assertEquals(popularResponses.size.toLong(), popularEntities.size.toLong())
    }

    @Test
    fun getAllModulesByCourse() {
        `when`<List<ModuleResponse>>(remote.getModules(courseId)).thenReturn(moduleResponses)
        val moduleEntities = academyRepository.getAllModulesByCourse(courseId)
        verify<RemoteDataSource>(remote).getModules(courseId)
        assertNotNull(moduleEntities)
        assertEquals(moduleResponses.size.toLong(), moduleEntities.size.toLong())
    }

    @Test
    fun getBookmarkedCourses() {
        `when`<List<CourseResponse>>(remote.getAllCourses()).thenReturn(courseResponses)
        val courseEntities = academyRepository.getBookmarkedCourses()
        verify<RemoteDataSource>(remote).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponses.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getContent() {
        `when`<List<ModuleResponse>>(remote.getModules(courseId)).thenReturn(moduleResponses)
        `when`<ContentResponse>(remote.getContent(moduleId)).thenReturn(content)
        val resultModule = academyRepository.getContent(courseId, moduleId)
        verify<RemoteDataSource>(remote).getContent(moduleId)
        assertNotNull(resultModule)
        assertEquals(content.content, resultModule.contentEntity?.content)
    }

    @Test
    fun getCourseWithModules() {
        `when`<List<CourseResponse>>(remote.getAllCourses()).thenReturn(courseResponses)
        val resultCourse = academyRepository.getCourseWithModules(courseId)
        verify<RemoteDataSource>(remote).getAllCourses()
        assertNotNull(resultCourse)
        assertEquals(courseResponses[0].title, resultCourse.title)
    }

 */
}