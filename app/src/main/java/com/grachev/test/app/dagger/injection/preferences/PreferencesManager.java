package com.grachev.test.app.dagger.injection.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import static com.grachev.test.app.utils.Constants.DEFAULT_INCREMENT;
import static com.grachev.test.app.utils.Constants.DEFAULT_MAXIMUM;
import static com.grachev.test.app.utils.Constants.DEFAULT_NUMBER;

/**
 * Created by Nick on 19.07.2017.
 */

public class PreferencesManager {
    final static String FILE_NAME = "preferences";
    final static String PREF_NUMBER = "number";
    final static String PREF_INCREMENT = "increment";
    final static String PREF_MAXIMUM = "maximum";

    private SharedPreferences preferences;

    public PreferencesManager(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    public void setNumber(int data) {
        getEditor().putInt(PREF_NUMBER, data).commit();
    }

    public int getNumber() {
        return preferences.getInt(PREF_NUMBER, DEFAULT_NUMBER);
    }

    public void setIncrement(int data) {
        getEditor().putInt(PREF_INCREMENT, data).commit();
    }

    public int getIncrement() {
        return preferences.getInt(PREF_INCREMENT, DEFAULT_INCREMENT);
    }

    public void setMaximum(int data) {
        getEditor().putInt(PREF_MAXIMUM, data).commit();
    }

    public int getMaximum() {
        return preferences.getInt(PREF_MAXIMUM, DEFAULT_MAXIMUM);
    }
}
