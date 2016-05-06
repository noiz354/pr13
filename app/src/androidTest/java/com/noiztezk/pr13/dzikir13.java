package com.noiztezk.pr13;

import com.noiztezk.pr13.MainActivity2;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.os.SystemClock;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static org.hamcrest.Matchers.*;
import android.test.suitebuilder.annotation.LargeTest;
import static com.checkdroid.crema.EspressoPlus.*;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.view.View;

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

    /**
     * Test for Dzkir-13
     * @author - M Normansyah Putra ND
     * Generated using Barista - http://checkdroid.com/barista
     */
    @Test
    public void test_Dzikir9() {
        onView(withId(R.id.recylerview_main_activity2)).perform(RecyclerViewActions.actionOnItemAtPosition(11, click()));
        onView(withId(R.id.arabic)).check(matches(withText("سُبْحَانَ اللّهِ ، والْحَمْدُللّهِ ، وَ لا اِلهَ اِلَّا اللّهُ ، وَ اللّهُ اَكْبَرُ ، وَ لا حَوْلَ وَ لا قُوَّةَ اِلَّا بِاللّهِ -")));
    }

    /**
     * Test for Dzkir-13
     * @author - M Normansyah Putra ND
     * Generated using Barista - http://checkdroid.com/barista
     */
    @Test
    public void test_Dzikir1() {
//        tapRecyclerViewItem(R.id.recylerview_main_activity2, 12);
//        onView(withId(R.id.recylerview_main_activity2)).perform(RecyclerViewActions.actionOnItemAtPosition(12, click()));

        onView(withId(R.id.recylerview_main_activity2)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.text)));

//        SystemClock.sleep(1000);

        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());

        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.arabic)).check(matches(isDisplayed()));
        onView(withId(R.id.btnNumNotif)).check(matches(isDisplayed()));
    }

    public static void tapRecyclerViewItem(int recyclerViewId, int position) {
        onView(withId(recyclerViewId)).perform(scrollToPosition(position));
        onView(withRecyclerView(recyclerViewId).atPosition(position)).perform(click());
    }

    // Convenience helper
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                if (v != null) {
                    v.performClick();
                }
            }
        };
    }
}
