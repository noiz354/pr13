package com.noiztezk.pr13;

import com.noiztezk.db.Dzikir;
import com.noiztezk.db.DzikirDatabase;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.lang.reflect.Field;

/**
 * Created by noiz354 on 4/29/16.
 */
public class MockPRThirteenAplication extends PRThirteenApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.destroy();
//        resetSingleton(FlowManager.class, "mDatabaseHolder");
        getmNetComponent().provideApplication().deleteDatabase(DzikirDatabase.NAME + ".db");
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    @Override
    protected NetComponent createComponent() {
        return DaggerTestComponent.builder()
                .mockAppModule(new MockAppModule(this))
                .mockNetModule(new MockNetModule())
                .build();
    }

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
}
