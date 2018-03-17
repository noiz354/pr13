package com.noiztezk.pr13.test.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by noiz354 on 4/29/16.
 */
@Module
public class MockAppModule {
    Application application;

    public MockAppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }
}
