package com.grachev.test.data;

import com.grachev.test.app.dagger.injection.preferences.PreferencesManager;
import com.grachev.test.presentation.settings.SettingsContract;

import javax.inject.Inject;

/**
 * Модель данных для SettingsFragment
 */
public class SettingsModel implements SettingsContract.Model {

    /**
     * Хэлпер работы с SharedPreferences
     */
    private PreferencesManager preferences;

    @Inject
    public SettingsModel(PreferencesManager preferences) {
        this.preferences = preferences;
    }

    /**
     * Записываем инкремент
     * @param increment
     */
    @Override
    public void setIncrement(int increment) {
        preferences.setIncrement(increment);
    }

    /**
     * получаем инкремент
     * @return
     */
    @Override
    public int getIncrement() {
        return preferences.getIncrement();
    }

    /**
     * записываем максимум
     * @param maximum
     */
    @Override
    public void setMaximum(int maximum) {
        preferences.setMaximum(maximum);
    }


    /**
     * получаем максимум
     * @return
     */
    @Override
    public int getMaximum() {
        return preferences.getMaximum();
    }


    /**
     * Обнуляем счетчик
     */
    @Override
    public void resetNumber() {
        preferences.setNumber(0);
    }
}
