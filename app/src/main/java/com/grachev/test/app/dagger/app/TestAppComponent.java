package com.grachev.test.app.dagger.app;

import android.content.Context;

import com.grachev.test.app.dagger.injection.preferences.PreferencesManager;
import com.grachev.test.app.dagger.injection.preferences.PreferencesModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by nikolay on 21.08.17.
 */

@Singleton
@Component(
        modules = {
                TestAppModule.class,
                PreferencesModule.class
        }
)
public interface TestAppComponent {
    void inject(TestApp app);

    Context appContext();
    PreferencesManager providesPreferences();
}
