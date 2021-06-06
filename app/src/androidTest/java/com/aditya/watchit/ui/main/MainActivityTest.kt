package com.aditya.watchit.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.aditya.watchit.R
import com.aditya.watchit.utils.DummyData
import com.aditya.watchit.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest{
    private val dummyMovie = DummyData.generateMovieDummy()
    private val dummyTv = DummyData.generateTvDummy()
    private val dummyPopular = DummyData.generatePopularDummy()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadPopular(){
        onView(withId(R.id.rv_popular)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_popular)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailPopular(){
        onView(withId(R.id.rv_popular)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(withText(dummyPopular[0].title)))
        onView(withId(R.id.tv_type)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_type)).check(matches(withText(dummyPopular[0].type)))
        onView(withId(R.id.tv_detail_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_description)).check(matches(withText(dummyPopular[0].description)))
    }

    @Test
    fun loadMovie(){

        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadTvSeries(){
        onView(withText(R.string.tv)).perform(click())
        onView(withId(R.id.rv_tv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_series)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTv.size))
    }

    @Test
    fun loadDetailMovie(){
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.tv_type)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_type)).check(matches(withText(dummyMovie[0].type)))
        onView(withId(R.id.tv_detail_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_description)).check(matches(withText(dummyMovie[0].description)))
    }

    @Test
    fun loadDetailTvSeries(){
        onView(withText(R.string.tv)).perform(click())
        onView(withId(R.id.rv_tv_series)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(withText(dummyTv[0].title)))
        onView(withId(R.id.tv_type)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_type)).check(matches(withText(dummyTv[0].type)))
        onView(withId(R.id.tv_detail_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_description)).check(matches(withText(dummyTv[0].description)))
    }

    @Test
    fun loadFavorit(){
        onView(withId(R.id.btn_to_favorit)).perform(click())
        onView(withId(R.id.rv_favorit)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorit)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_type)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_description)).check(matches(isDisplayed()))
        onView(isRoot()).perform(ViewActions.pressBack())
    }
}