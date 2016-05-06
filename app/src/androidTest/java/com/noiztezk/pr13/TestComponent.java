package com.noiztezk.pr13;

import com.noiztezk.pr13.model.AudioTypeTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MockAppModule.class, MockNetModule.class})
public interface TestComponent extends NetComponent{
    void inject(AudioTypeTest audioTypeTest);
    void inject(MainActivityTest mainActivityTest);
    void inject(MainActivity2Test mainActivity2Test);
}