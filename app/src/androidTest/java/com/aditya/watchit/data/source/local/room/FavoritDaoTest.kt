package com.aditya.watchit.data.source.local.room

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.LivePagedListBuilder
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.utils.LiveDataTestUtil
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoritDaoTest {
    private lateinit var database: SourceDatabase
    private lateinit var dao: FavoritDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, SourceDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.favoritDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetFilm() {
        val film = FilmEntity("Title", "Movies", "desc", "banner")
        dao.insertToFilm(listOf(film))

        val loaded = LiveDataTestUtil.getValue(dao.getFilm("Title", "Movies"))
        assertEquals(film.title, loaded.title)
    }

    @Test
    fun addAndRemoveFavorit() {
        val favorit = FavoritEntity("Fav", "Movies", "desc", "banner", true)
        dao.addToFavorit(favorit)
        val paged = LivePagedListBuilder(dao.getFavorit(), 20).build()
        var favList = LiveDataTestUtil.getValue(paged)
        assertEquals(1, favList.size)

        dao.deleteFavorit(favorit)
        val pagedAfter = LivePagedListBuilder(dao.getFavorit(), 20).build()
        favList = LiveDataTestUtil.getValue(pagedAfter)
        assertEquals(0, favList.size)
    }
}
