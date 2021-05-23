package com.aditya.watchit.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.aditya.watchit.R
import com.aditya.watchit.utils.DummyData
import org.junit.Rule
import org.junit.Test

class MainActivityTest{
    private val dummyMovie = DummyData.generateMovieDummy()
    private val dummyTv = DummyData.generateTvDummy()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadPopular(){
        onView(withId(R.id.rv_popular)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_popular)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailPopular(){
        onView(withId(R.id.rv_popular)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.tv_type)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_type)).check(ViewAssertions.matches(withText(dummyMovie[0].type)))
        onView(withId(R.id.tv_detail_description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_description)).check(ViewAssertions.matches(withText(dummyMovie[0].description)))
    }

    @Test
    fun loadMovie(){
        onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadTvSeries(){
        onView(withText(R.string.tv)).perform(ViewActions.click())
        onView(withId(R.id.rv_tv_series)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tv_series)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTv.size))
    }

    @Test
    fun loadDetailMovie(){
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
        onView(withText(R.string.tv)).perform(ViewActions.click())
        onView(withId(R.id.rv_tv_series)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(withText(dummyTv[0].title)))
        onView(withId(R.id.tv_type)).check(ViewAssertions.matches(withText(dummyTv[0].type)))
        onView(withId(R.id.tv_detail_description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_description)).check(ViewAssertions.matches(withText(dummyTv[0].description)))
    }
}