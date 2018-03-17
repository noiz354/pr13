package com.noiztezk.pr13;

import com.noiztezk.db.DzikirDatabase;
import com.noiztezk.pr13.test.dagger.DaggerTestComponent;
import com.noiztezk.pr13.test.dagger.MockAppModule;
import com.noiztezk.pr13.test.dagger.MockNetModule;
import com.noiztezk.pr13.test.dagger.TestComponent;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.lang.reflect.Field;

/**
 * Created by noiz354 on 4/29/16.
 */
public class MockPRThirteenAplication extends App {

    private static void resetSingleton(Class clazz, String fieldName) {
        Field instance;
        try {
            instance = clazz.getDeclaredField(fieldName);
            instance.setAccessible(true);
            instance.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void onCreateReal() {
        FlowManager.destroy();
//        resetSingleton(FlowManager.class, "globalDatabaseHolder");
        TestComponent testComponent = (TestComponent) getMNetComponent();
        testComponent.provideApplication().deleteDatabase(DzikirDatabase.NAME + ".db");
        FlowManager.init(new FlowConfig.Builder(this).build());
        testComponent.sharedPreferences().edit().clear().apply();
    }

    @Override
    protected NetComponent createComponent() {
        return DaggerTestComponent.builder()
                .mockAppModule(new MockAppModule(this))
                .mockNetModule(new MockNetModule())
                .build();
    }
}
