package com.grachev.test.app.dagger.app;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nikolay on 21.08.17.
 */

@Module
public class TestAppModule {

    private final TestApp app;
    private final Context appContext;

    public TestAppModule(TestApp app) {
        this.appContext = app;
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }


    @Provides
    @Singleton
    Context provideAppContext() {
        return appContext;
    }


}
