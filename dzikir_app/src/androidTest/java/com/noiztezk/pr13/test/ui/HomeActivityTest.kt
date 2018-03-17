package com.noiztezk.pr13.test.ui

import android.content.Intent
import android.content.SharedPreferences
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.gson.Gson
import com.noiztezk.pr13.App
import com.noiztezk.pr13.HomeActivity
import com.noiztezk.pr13.R
import com.noiztezk.pr13.model.Dzikir
import com.noiztezk.pr13.presenters.HomeView
import com.noiztezk.pr13.test.dagger.TestComponent
import com.noiztezk.pr13.test.recyclerview.RecyclerViewInteraction
import com.noiztezk.pr13.test.utils.TestUtils
import org.hamcrest.Matchers.allOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import javax.inject.Inject

/**
 * Created by normansyahputa on 3/17/18.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeActivityTest {
    val LAST_ITEM = "ُبْحَانَ اللّهِ ، والْحَمْدُللّهِ ، وَ لا اِلهَ اِلَّا اللّهُ ، وَ اللّهُ اَكْبَرُ"

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(
            HomeActivity::class.java,
            true, // initialTouchMode
            false // launchActivity. False so we can customize the intent per test method
    )

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var gson: Gson

    internal var readjsonDzikir: List<Dzikir>? = null

    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as App
        val component = app.mNetComponent as TestComponent
        component.inject(this)

        try {
            readjsonDzikir = HomeActivity.getData(InstrumentationRegistry.getContext(), gson!!)
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }

    }

    @Test
    fun checkFirstRow() {
        mActivityRule.launchActivity(Intent())

        readjsonDzikir?.let {
            for (i in 0..it.size - 1) {
                RecyclerViewInteraction.onRecyclerView<Dzikir>(ViewMatchers.withId(R.id.recylerview_main_activity))
                        .withItems(it)
                        .check { item, view, e ->
                            matches(hasDescendant(withText(item.text)))
                                    .check(view, e)
                        }
            }
        }
    }

    @Test
    fun performClickAtRecyclerView() {
        mActivityRule.launchActivity(Intent())

        onView(withId(R.id.recylerview_main_activity)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(12, TestUtils.clickChildViewWithId(R.id.text)))


        readjsonDzikir?.let {
            onView(withId(R.id.arabic)).check(matches(withText(it.get(12).text)))
        }

    }

    @Test
    fun showFirstTimeSalam() {
        sharedPreferences.edit().remove(HomeView.FIRST_TIME).apply()

        mActivityRule.launchActivity(Intent())

        onView(allOf<View>(withId(android.support.design.R.id.snackbar_text), withText("Assalamualaikum ?")))
                .check(matches(isDisplayed()))
    }

    @Test
    fun lastItem_NotDisplayed() {
        mActivityRule.launchActivity(Intent())

        onView(withText(LAST_ITEM)).check(doesNotExist())
    }


    @Test
    fun performSettingClick() {
        mActivityRule.launchActivity(Intent())

        onView(ViewMatchers.withId(R.id.action_settings)).perform(click())
    }

    @Test
    fun checkChangeHeaderName() {
        mActivityRule.launchActivity(Intent())

        Assert.assertEquals("Test header ", mActivityRule.activity.supportActionBar!!.title!!.toString(), "Home")
    }
}