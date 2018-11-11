package com.grachev.test.data;

import com.grachev.test.app.dagger.injection.preferences.PreferencesManager;
import com.grachev.test.presentation.settings.SettingsContract;

import javax.inject.Inject;

/**
 * Created by Nick on 20.07.2017.
 */

public class SettingsModel implements SettingsContract.Model {

    private PreferencesManager preferences;

    @Inject
    public SettingsModel(PreferencesManager preferences) {
        this.preferences = preferences;
    }

    @Override
    public void setIncrement(int increment) {
        preferences.setIncrement(increment);
    }

    @Override
    public int getIncrement() {
        return preferences.getIncrement();
    }

    @Override
    public void setMaximum(int maximum) {
        preferences.setMaximum(maximum);
    }


    @Override
    public int getMaximum() {
        return preferences.getMaximum();
    }


    // обнуление
    @Override
    public void resetNumber() {
        preferences.setNumber(0);
    }
}
