package com.noiztezk.pr13;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * Created by noiz354 on 4/29/16.
 */
public class MockTestRunner  extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, MockPRThirteenAplication.class.getName(), context);
    }
}
