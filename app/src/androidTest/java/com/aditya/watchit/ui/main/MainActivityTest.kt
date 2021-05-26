package com.aditya.watchit.ui.main

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.aditya.watchit.R
import com.aditya.watchit.utils.DummyData
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{
    private val dummyMovie = DummyData.generateMovieDummy()
    private val dummyTv = DummyData.generateTvDummy()
    private val dummyPopular = DummyData.generatePopularDummy()
    private lateinit var instrumentalContext: Context

    @Before
    fun setUp() {
        instrumentalContext = InstrumentationRegistry.getInstrumentation().targetContext
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun loadPopular(){
        try {
            Thread.sleep(2500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        onView(withId(R.id.rv_popular)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_popular)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailPopular(){
        try {
            Thread.sleep(2500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        onView(withId(R.id.rv_popular)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(withText(dummyPopular[0].title)))
        onView(withId(R.id.tv_type)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_type)).check(ViewAssertions.matches(withText(dummyPopular[0].type)))
        onView(withId(R.id.tv_detail_description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_description)).check(ViewAssertions.matches(withText(dummyPopular[0].description)))
    }

    @Test
    fun loadMovie(){
        try {
            Thread.sleep(2500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadTvSeries(){
        onView(withText(R.string.tv)).perform(ViewActions.click())
        try {
            Thread.sleep(2500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        onView(withId(R.id.rv_tv_series)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tv_series)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTv.size))
    }

    @Test
    fun loadDetailMovie(){
        try {
            Thread.sleep(2500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.tv_type)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_type)).check(ViewAssertions.matches(withText(dummyMovie[0].type)))
        onView(withId(R.id.tv_detail_description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_description)).check(ViewAssertions.matches(withText(dummyMovie[0].description)))
    }

    @Test
    fun loadDetailTvSeries(){
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        onView(withText(R.string.tv)).perform(ViewActions.click())
        onView(withId(R.id.rv_tv_series)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(withText(dummyTv[0].title)))
        onView(withId(R.id.tv_type)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_type)).check(ViewAssertions.matches(withText(dummyTv[0].type)))
        onView(withId(R.id.tv_detail_description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_description)).check(ViewAssertions.matches(withText(dummyTv[0].description)))
    }
}