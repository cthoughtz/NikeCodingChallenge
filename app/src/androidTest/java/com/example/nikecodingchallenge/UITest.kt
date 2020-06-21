package com.example.nikecodingchallenge

import android.content.res.Resources
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.nikecodingchallenge.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.EnumSet.allOf
import org.hamcrest.Matcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.nikecodingchallenge.adapter.WordsListAdapter


@RunWith(AndroidJUnit4::class)
class UITest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun UI() {

        onView(withId(R.id.sort)).perform(click())
        onView(withId(R.id.search_button)).perform(click())
        onView(withId(R.id.search_src_text)).perform(replaceText("hire me"), pressImeActionButton())
        //Screenshot with spoon

        // Making Thread sleep so that User can see the outcome
        Thread.sleep(5000)
        onView(withId(R.id.sort)).perform(click())
        onView(withId(R.id.recyclerView)).perform(swipeUp())

        Thread.sleep(5000)
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, clickOnViewChild(R.id.share_button)))
        Thread.sleep(5000)

    }

    fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) = click().perform(uiController, view.findViewById<View>(viewId))
    }








}