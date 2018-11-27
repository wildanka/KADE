package com.example.dan.kadesubmission2.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.dan.kadesubmission2.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.EnumSet.allOf

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField var mainActivity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testSearchTeam(){
        //TODO : Lihat Next Match
        onView(withId(R.id.vp_fragment_fixtures)).perform(swipeLeft())
    }

    @Test
    fun testRecyclerViewBehaviour() {
        //TODO : menambahkan tim ke favorite
        onView(withId(R.id.navigation_teams)).perform(click())
        Thread.sleep(7000) //idling menunggu response dari network
        onView(withId(R.id.rv_list_teams))
                .check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_teams)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.rv_list_teams)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click())
        )

        Thread.sleep(7000) //idling menunggu response dari network
        onView(withId(R.id.add_to_favorites)).perform(click())
    }


}