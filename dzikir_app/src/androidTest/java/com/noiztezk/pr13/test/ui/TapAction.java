package com.noiztezk.pr13.test.ui;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.noiztezk.pr13.HomeActivity;
import com.noiztezk.pr13.R;
import com.noiztezk.pr13.test.utils.TestUtils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.checkdroid.crema.EspressoPlus.takeScreenShot;
import static com.checkdroid.crema.EspressoPlus.withXPath;
import static org.hamcrest.Matchers.allOf;

/**
 * This is test tap at list and perform click at certain position.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TapAction {

    @Rule
    public final ActivityTestRule<HomeActivity> main = new ActivityTestRule<>(HomeActivity.class);

    /**
     * Test for Dzkir-13
     * @author - M Normansyah Putra ND
     * Generated using Barista - http://checkdroid.com/barista
     */
    @Test
    public void perform_Tap_At_Index_1() {
        onView(ViewMatchers.withId(R.id.recylerview_main_activity)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.counterButton)).perform(click());
        onView(withId(R.id.btnNumNotif)).check(matches(withText("5")));
    }

    /**
     * Test for Dzkir-13
     * @author - M Normansyah Putra ND
     * Generated using Barista - http://checkdroid.com/barista
     *
     * android.support.test.espresso.NoMatchingViewException: No views in hierarchy found matching: with id: com.noiztezk.pr13:id/arabic
     */
    @Test
    public void test_Dzikir7() {
        onView(withId(R.id.recylerview_main_activity)).perform(RecyclerViewActions.actionOnItemAtPosition(8, click()));
        onView(withId(R.id.arabic)).check(matches(withText("بِسْمِ اللَّهِ الَّذِیْ لَا یَضُرُّ مَعَ اسْمِهِ شَیْ ءٌ فِیْ الْاَرْضِ وَلَا فِی السَّمَآءِ وَھُوَ السَّمِیْعُ الْعَلِیْمُ")));
    }

    /**
     * Test for Dzkir-13
     * @author - M Normansyah Putra ND
     * Generated using Barista - http://checkdroid.com/barista
     */
    @Test
    public void test_Dzikir8() {
        takeScreenShot();
        onView(withId(R.id.recylerview_main_activity)).perform(RecyclerViewActions.scrollToPosition(12));
        onView(withId(R.id.recylerview_main_activity)).perform(RecyclerViewActions.actionOnItemAtPosition(12, click()));
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
        onView(withId(R.id.recylerview_main_activity)).perform(RecyclerViewActions.actionOnItemAtPosition(11, click()));
        onView(allOf(withId(R.id.arabic), withContentDescription("Hello!"))).check(matches(withText("سُبْحَانَ اللّهِ ، والْحَمْدُللّهِ ، وَ لا اِلهَ اِلَّا اللّهُ ، وَ اللّهُ اَكْبَرُ ، وَ لا حَوْلَ وَ لا قُوَّةَ اِلَّا بِاللّهِ -")));
    }

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
    public void test_Dzikir10() {
        onView(withId(R.id.recylerview_main_activity)).perform(RecyclerViewActions.actionOnItemAtPosition(11, click()));
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

        onView(withId(R.id.recylerview_main_activity)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, TestUtils.clickChildViewWithId(R.id.text)));

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


}

