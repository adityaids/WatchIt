package com.aditya.watchit.ui.favorit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.source.local.LocalDataSource
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.ui.main.MainViewModel
import com.aditya.watchit.vo.Resource
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoritViewModelTest {
    private lateinit var viewModel: FavoritViewModel
    private val local = Mockito.mock(LocalDataSource::class.java)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var pagedList: PagedList<FavoritEntity>

    @Before
    fun setUp(){
        viewModel = FavoritViewModel(dataRepository)
    }

    @Mock
    private lateinit var observer: Observer<PagedList<FavoritEntity>>

    @Test
    fun getAllFavoit(){
        val dummyfavorit = pagedList
        Mockito.`when`(dummyfavorit.size).thenReturn(5)
        val favorit = MutableLiveData<PagedList<FavoritEntity>>()
        favorit.value = dummyfavorit

        Mockito.`when`(dataRepository.getAllFavorit()).thenReturn(favorit)
        val favoritEntities = viewModel.getAllFavorit()
        Mockito.verify(dataRepository).getAllFavorit()
        Assert.assertNotNull(favoritEntities)
        Assert.assertEquals(5, favoritEntities.value?.size)

        viewModel.getAllFavorit().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyfavorit)
    }
}