package com.noiztezk.pr13;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
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

    public static final String LAST_ITEM = "ُبْحَانَ اللّهِ ، والْحَمْدُللّهِ ، وَ لا اِلهَ اِلَّا اللّهُ ، وَ اللّهُ اَكْبَرُ";
    /**
     * ini pengganti dari {@link android.test.ActivityInstrumentationTestCase2}
     * rules adalah interceptor yang dieksekusi dari setiap metode dan dijalankan sebelum setup code {@link Before @Before }
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void lastItem_NotDisplayed(){
        onView(withText(LAST_ITEM)).check(doesNotExist());
    }


    @Test
    public void performSettingClick(){
        onView(withId(R.id.action_settings)).perform(click());

        // TODO add checking if toast is created.
    }

    @Test
    public void checkChangeHeaderName(){
        Assert.assertEquals("Test header ", mActivityRule.getActivity().getSupportActionBar().getTitle().toString(), "Home");
    }
}
