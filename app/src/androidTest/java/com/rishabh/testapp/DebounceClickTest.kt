package com.rishabh.testapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.core.StringContains.containsString
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DebounceClickTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun initialize() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.button)).perform(ViewActions.click())
    }

    @Test
    fun blankFragmentIsDisplayed() {
        onView(withId(R.id.vg_blank_fragment)).check(matches(isDisplayed()))
    }

    @Test
    fun fasterTapWithinASecond() {
        onView(withId(R.id.btnFrag)).perform(ViewActions.click())
        onView(withId(R.id.btnFrag)).perform(ViewActions.click())
        onView(withId(R.id.btnFrag)).perform(ViewActions.click())
        onView(withId(R.id.tv_count)).check(matches(withText(containsString("1"))))
    }

    @Test
    fun slowerTapWithinASecond() {
        onView(withId(R.id.btnFrag)).perform(ViewActions.click())
        Thread.sleep(1000)
        onView(withId(R.id.btnFrag)).perform(ViewActions.click())

        onView(withId(R.id.tv_count)).check(matches(withText(containsString("2"))))
    }
}