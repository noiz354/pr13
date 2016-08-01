package com.noiztezk.pr13.test.dagger;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.noiztezk.pr13.NetComponent;
import com.noiztezk.pr13.test.dagger.MockAppModule;
import com.noiztezk.pr13.test.dagger.MockNetModule;
import com.noiztezk.pr13.test.db.AudioTypeTest;
import com.noiztezk.pr13.test.ui.MainActivity2Test;
import com.noiztezk.pr13.test.ui.MainActivityTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MockAppModule.class, MockNetModule.class})
public interface TestComponent extends NetComponent {
    SharedPreferences sharedPreferences();
    Gson gson();
    void inject(AudioTypeTest audioTypeTest);
    void inject(MainActivityTest mainActivityTest);
    void inject(MainActivity2Test mainActivity2Test);
}