package com.noiztezk.pr13;

import com.noiztezk.pr13.MainActivity2;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static org.hamcrest.Matchers.*;
import android.test.suitebuilder.annotation.LargeTest;
import static com.checkdroid.crema.EspressoPlus.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class dzikir13 {

    @Rule
    public final ActivityTestRule<MainActivity2> main = new ActivityTestRule<>(MainActivity2.class);

    /**
     * Test for Dzkir-13
     * @author - M Normansyah Putra ND
     * Generated using Barista - http://checkdroid.com/barista
     */
    @Test
    public void test_dzikir13() {
        onView(withText("لا اِلهَ اِلَّا اللّهُ وَحْدَهُ لا شَرِيكَ لَهُ ، لَهُ الْمُلْكُ وَ لَهُ الْحَمْدُ وَ هُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ")).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.action_settings)).perform(click());
        onView(withText("لا اِلهَ اِلَّا اللّهُ وَحْدَهُ لا شَرِيكَ لَهُ ، لَهُ الْمُلْكُ وَ لَهُ الْحَمْدُ وَ هُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ")).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        takeScreenShot();
        onView(withXPath("/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout")).check(matches(isDisplayed()));
        onView(withXPath("/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout")).check(matches(isEnabled()));
    }
}
