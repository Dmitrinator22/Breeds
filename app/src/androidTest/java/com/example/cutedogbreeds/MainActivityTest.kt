package com.example.cutedogbreeds

import android.view.View
import android.widget.AdapterView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
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
    fun list_Of_Breeds_Click_to_BreedActivity(){

        onView(withId(R.id.updateView)).perform(click())
        onView(withId(R.id.listview)).check(matches(isDisplayed()))

        onData(anything()).inAdapterView(withId(R.id.listview)).atPosition(LIST_ITEM).perform(click())

        onView(withId(R.id.infoLinearLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.deleteImg)).check(matches(isDisplayed()))
        onView(withId(R.id.loadImg)).check(matches(isDisplayed()))
        onView(withId(R.id.imgV)).check(matches(isDisplayed()))

    }

    @Test
    fun check_object_is_not_in_list(){

        onView(withId(R.id.updateView)).perform(click())
        onView(withId(R.id.listview)).check(matches(not(withAdaptedData(withContentDescription("Labrador")))))

    }

    @Test
    fun check_object_is_in_list(){

        onView(withId(R.id.updateView)).perform(click())


    }

    @Test
    fun back_to_MainActivity(){

        onView(withId(R.id.updateView)).perform(click())
        onData(anything()).inAdapterView(withId(R.id.listview)).atPosition(LIST_ITEM).perform(click())

        onView(withId(R.id.infoLinearLayout)).perform(swipeRight())
        onView(withId(R.id.main)).check(matches(isDisplayed()))

    }

    private fun withAdaptedData(dataMatcher: Matcher<View>): Matcher<View> {
        return object : TypeSafeMatcher<View>() {

            override fun describeTo(description: Description) {
                description.appendText("with class name: ")
                dataMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View) : Boolean {
                if (view !is AdapterView<*>) {
                    return false
                }

                val adapter = view.adapter
                for (i in 0 until adapter.count) {
                    if (dataMatcher.matches(adapter.getItem(i))) {
                        return true
                    }
                }

                return false
            }
        }
    }


}