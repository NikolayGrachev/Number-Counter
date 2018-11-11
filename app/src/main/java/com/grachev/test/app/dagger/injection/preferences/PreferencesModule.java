package com.grachev.test.app.dagger.injection.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.grachev.test.app.dagger.injection.preferences.PreferencesManager.FILE_NAME;

/**
 * Created by nikolaygrachev on 18.10.2017.
 */

@Module
public class PreferencesModule {

    @Provides
    @Singleton
    public SharedPreferences providePreferences (Context context) {
        return context.getSharedPreferences(FILE_NAME, 0);
    }

    @Provides
    @Singleton
    public PreferencesManager providePreferencesManager(SharedPreferences preferences) {
        return new PreferencesManager(preferences);
    }
}