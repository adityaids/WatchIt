package com.aditya.watchit.ui.detail

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aditya.watchit.R
import com.aditya.watchit.data.source.local.entity.PopularEntity
import com.aditya.watchit.utils.DummyData
import com.aditya.watchit.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    private val dummy = DummyData.generatePopularDummy()[0]

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val intent = Intent(context, DetailActivity::class.java)
        val entity = PopularEntity(dummy.title, dummy.type, dummy.description, dummy.banner)
        intent.putExtra(DetailActivity.EXTRA_DATA_POPULAR, entity)
        ActivityScenario.launch<DetailActivity>(intent)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun displayFilmDetails() {
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(withText(dummy.title)))
        onView(withId(R.id.tv_type)).check(matches(withText(dummy.type)))
        onView(withId(R.id.tv_detail_description)).check(matches(withText(dummy.description)))
    }
}
