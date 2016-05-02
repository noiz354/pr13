package com.noiztezk.pr13;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import com.google.gson.Gson;
import com.noiztezk.pr13.model.Dzikir;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.fail;

/**
 * Created by noiz354 on 5/2/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivity2Test {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    Gson gson;

    List<Dzikir> readjsonDzikir;

    @Before
    public void setUp(){
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        PRThirteenApplication app
                = (PRThirteenApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = (TestComponent) app.getmNetComponent();
        component.inject(this);

        try {
            readjsonDzikir = MainActivity2.getData(InstrumentationRegistry.getContext(),gson);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

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
    public void performCheckArabicContent(){
        mActivityRule.launchActivity(new Intent());

            RecyclerViewInteraction.
                    <Dzikir>onRecyclerView(withId(R.id.recylerview_main_activity2))
                    .withItems(readjsonDzikir)
                    .check(new RecyclerViewInteraction.ItemViewAssertion<Dzikir>() {
                        @Override
                        public void check(Dzikir item, View view, NoMatchingViewException e) {
                            matches(hasDescendant(withText(item.getText())))
                                    .check(view, e);
                        }
                    });

    }

    @Test
    public void performClickAtRecyclerView(){
        mActivityRule.launchActivity(new Intent());

        onView(withId(R.id.recylerview_main_activity2)).perform(
                RecyclerViewActions.actionOnItemAtPosition(12, TestUtils.clickChildViewWithId(R.id.text)));


        onView(withId(R.id.arabic)).check(matches(withText(readjsonDzikir.get(12).getText())));
    }
}
