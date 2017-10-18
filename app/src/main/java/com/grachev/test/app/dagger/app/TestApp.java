package com.grachev.test.app.dagger.app;

import android.app.Application;
import android.content.Context;

import com.grachev.test.app.dagger.injection.preferences.PreferencesModule;

import java.io.File;


/**
 * Created by nikolay on 21.08.17.
 */

public class TestApp extends Application {
    private TestAppComponent TestAppComponent;

    public static TestApp get(Context context) {
        return (TestApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildGraphAndInject();
    }


    public TestAppComponent getTestAppComponent() {
        return TestAppComponent;
    }

    public void buildGraphAndInject() {


        TestAppComponent = DaggerTestAppComponent.builder()
                .testAppModule(new TestAppModule(this))
                .preferencesModule(new PreferencesModule())
                .build();
        TestAppComponent.inject(this);
    }
}
