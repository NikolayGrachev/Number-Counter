package com.grachev.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;

import com.grachev.test.app.dagger.injection.preferences.PreferencesManager;
import com.grachev.test.data.Incrementer;

import org.junit.Before;
import org.junit.Test;

import static com.grachev.test.app.dagger.injection.preferences.PreferencesManager.FILE_NAME;
import static org.junit.Assert.assertEquals;


public class PreferencesTest {

    private PreferencesManager preferencesManager;

    @Before
    public void setPref() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        SharedPreferences preferences = appContext.getSharedPreferences(FILE_NAME, 0);
        this.preferencesManager = new PreferencesManager(preferences);
    }

    @Test
    public void saveAndReadValues() throws Exception {

        int input = 10;
        preferencesManager.setNumber(input);
        int result = preferencesManager.getNumber();

        assertEquals(input, result);
    }
}
