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
import android.support.test.espresso.contrib.RecyclerViewActions;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class Dzikir8 {

    @Rule
    public final ActivityTestRule<MainActivity2> main = new ActivityTestRule<>(MainActivity2.class);

    /**
     * Test for Dzkir-13
     * @author - M Normansyah Putra ND
     * Generated using Barista - http://checkdroid.com/barista
     */
    @Test
    public void test_Dzikir8() {
        takeScreenShot();
        onView(withId(R.id.recylerview_main_activity2)).perform(RecyclerViewActions.scrollToPosition(12));
        onView(withId(R.id.recylerview_main_activity2)).perform(RecyclerViewActions.actionOnItemAtPosition(12, click()));
//        onView(withId(R.id.arabic)).check(matches(withText("سُبْحَانَ اللّهِ وَ بِحَمْدِهِ ، سُبْحَانَ اللّهِ الْعَظِيمِ")));
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.btnNumNotif)).check(matches(withText("bebas")));
    }

    /**
     * Test for Dzkir-13
     * @author - M Normansyah Putra ND
     * Generated using Barista - http://checkdroid.com/barista
     */
    @Test
    public void test_Dzikir9() {
        onView(withId(R.id.recylerview_main_activity2)).perform(RecyclerViewActions.actionOnItemAtPosition(11, click()));
        onView(allOf(withId(R.id.arabic), withContentDescription("Hello!"))).check(matches(withText("سُبْحَانَ اللّهِ ، والْحَمْدُللّهِ ، وَ لا اِلهَ اِلَّا اللّهُ ، وَ اللّهُ اَكْبَرُ ، وَ لا حَوْلَ وَ لا قُوَّةَ اِلَّا بِاللّهِ -")));
    }

}
