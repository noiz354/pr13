package com.noiztezk.pr13;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by Normansyah Putra on 8/2/2015.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    Gson gson;

    @Before
    public void setUp(){
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        PRThirteenApplication app
                = (PRThirteenApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = (TestComponent) app.getmNetComponent();
        component.inject(this);
    }

    public static final String LAST_ITEM = "ُبْحَانَ اللّهِ ، والْحَمْدُللّهِ ، وَ لا اِلهَ اِلَّا اللّهُ ، وَ اللّهُ اَكْبَرُ";

    /**
     * ini pengganti dari {@link android.test.ActivityInstrumentationTestCase2}
     * rules adalah interceptor yang dieksekusi dari setiap metode dan dijalankan sebelum setup code {@link Before @Before }
     */
    @Rule
    public ActivityTestRule<MainActivity2> mActivityRule = new ActivityTestRule<MainActivity2>(
            MainActivity2.class,
            true, // initialTouchMode
            false // launchActivity. False so we can customize the intent per test method
    );

    @Test
    public void showFirstTimeSalam(){
        sharedPreferences.edit().remove(MainActivity2.FIRST_TIME).apply();

        mActivityRule.launchActivity(new Intent());

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("Assalamualaikum ?")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void lastItem_NotDisplayed(){
        mActivityRule.launchActivity(new Intent());

        onView(withText(LAST_ITEM)).check(doesNotExist());
    }


    @Test
    public void performSettingClick(){
        mActivityRule.launchActivity(new Intent());

        onView(withId(R.id.action_settings)).perform(click());
    }

    @Test
    public void checkChangeHeaderName(){
        mActivityRule.launchActivity(new Intent());

        Assert.assertEquals("Test header ", mActivityRule.getActivity().getSupportActionBar().getTitle().toString(), "Home");
    }
}
