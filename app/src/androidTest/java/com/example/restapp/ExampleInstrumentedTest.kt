package com.example.restapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun ui_display_test() {
        onView(withId(R.id.heading)).check(matches(isDisplayed()))
        onView(withId(R.id.status_image)).check(matches(isDisplayed()))
        onView(withId(R.id.meme_image)).check(matches(isDisplayed()))
        onView(withId(R.id.exit_btn)).check(matches(isDisplayed()))
    }

    @Test
    fun click_screen_test() {
        onView(withId(R.id.images_container)).perform(click())

        onView(withId(R.id.heading)).check(matches(isDisplayed()))
        onView(withId(R.id.status_image)).check(matches(isDisplayed()))
        onView(withId(R.id.meme_image)).check(matches(isDisplayed()))
        onView(withId(R.id.exit_btn)).check(matches(isDisplayed()))
    }

}