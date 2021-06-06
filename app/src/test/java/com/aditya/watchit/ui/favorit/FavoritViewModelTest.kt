package com.aditya.watchit.ui.favorit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aditya.watchit.data.DataRepository
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.ui.main.MainViewModel
import com.aditya.watchit.vo.Resource
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoritViewModelTest {
    private lateinit var viewModel: FavoritViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Before
    fun setUp(){
        viewModel = FavoritViewModel(dataRepository)
    }

    @Mock
    private lateinit var observer: Observer<Resource<List<FavoritEntity>>>

    @Test
    fun getAllFavoit(){

    }

    fun deleteFavorit(){

    }
}