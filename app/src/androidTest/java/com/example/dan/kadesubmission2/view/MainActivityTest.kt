package com.example.dan.kadesubmission2.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
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

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField var mainActivity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        //TODO : buka next match, kemudian kembali ke menu utama
        onView(withId(R.id.rv_list_match))
                .check(matches(isDisplayed()))

        Thread.sleep(7000) //idling menunggu response dari network
        onView(withId(R.id.rv_list_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv_list_match)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        pressBack()

        //TODO : cek menu next match kemudian klik menu tersebut
        onView(withId(R.id.navigation_next_match)).perform(click())
        Thread.sleep(7000)

        //TODO : buka salah satu pertandingan
        onView(withId(R.id.rv_list_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(R.id.rv_list_match)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        Thread.sleep(7000)

        //TODO: lakukan klik terhadap menu add to/remove from favorites
        onView(withId(R.id.add_to_favorites)).perform(click())
        pressBack()

    }


}