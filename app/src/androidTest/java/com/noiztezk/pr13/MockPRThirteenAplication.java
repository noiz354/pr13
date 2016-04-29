package com.noiztezk.pr13;

/**
 * Created by noiz354 on 4/29/16.
 */
public class MockPRThirteenAplication extends PRThirteenApplication {
    @Override
    protected NetComponent createComponent() {
        return DaggerTestComponent.builder()
                .mockAppModule(new MockAppModule(this))
                .mockNetModule(new MockNetModule())
                .build();
    }
}
