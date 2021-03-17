package com.example.cutedogbreeds

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.CoreMatchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class MainActivityTest{



    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    val LIST_ITEM = 3
    val BREED = "Havanese"


    @Test
    fun test_activity_View() {
        //val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.main)).check(matches(isDisplayed()))

    }

    @Test
    fun test_updateButton_visibility(){
        //val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.updateView)).check(matches(isDisplayed()))

    }

    @Test
    fun  test_LinearLayoutAndListView_visibility(){
        //val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.linearLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.listview)).check(matches(isDisplayed()))

    }

    @Test
    fun press_updateButton_offline(){
        //val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.updateView)).perform(click())
        onView(withId(R.id.listview)).check(matches(isDisplayed()))

    }

    @Test
    fun listOfBreedsOffline(){

        onView(withId(R.id.updateView)).perform(click())
        onView(withId(R.id.listview)).check(matches(isDisplayed()))

        onData(anything()).inAdapterView(withId(R.id.listview)).atPosition(LIST_ITEM).perform(click())

        onView(withId(R.id.infoLinearLayout)).check(matches(isDisplayed()))

        onView(withId(R.id.deleteImg)).check(matches(isDisplayed()))
        onView(withId(R.id.loadImg)).check(matches(isDisplayed()))

        onView(withId(R.id.imgV)).check(matches(isDisplayed()))


    }



}