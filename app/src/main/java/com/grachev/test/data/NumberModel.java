package com.grachev.test.data;

import com.grachev.test.app.dagger.injection.preferences.PreferencesManager;
import com.grachev.test.presentation.number.NumberContract;

import javax.inject.Inject;

/**
 * Модель данных для NumberFragment
 */
public class NumberModel implements NumberContract.Model {

    /**
     * Значение счетчика
     */
    private int number;
    /**
     * Хэлпер работы с SharedPreferences
     */
    private PreferencesManager preferences;
    /**
     * Класс для расчетов следующего значения счетчика
     */
    private Incrementer incrementer;

    @Inject
    public NumberModel (PreferencesManager preferences) {
        this.preferences = preferences;

        init();
    }

    /**
     * Начальная инициализация, читаем сохраненное значение из префс
     * читаем настройки из префс
     */
    private void init() {
        number = preferences.getNumber();
        incrementer = new Incrementer(preferences.getIncrement(), preferences.getMaximum());
    }

    /**
     * сохраняем счетчик в префс (при вызове метода onPause)
     */
    @Override
    public void storeNumber() {
        preferences.setNumber(number);
    }

    /**
     * @return значение счетчика
     */
    @Override
    public int getNumber() {
        return number;
    }

    /**
     * Изменение текущего значения счетчика
     * @param number
     */
    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * увеличиваем число на инкремент
     * и сообщаем об изменениях в презентер
     * @param callback - колбэк для уведомления фрагмента, что изменения произошли
     */
    @Override
    public void incrementNumber(NumberChangesCallback callback) {
        setNumber(incrementer.calcNumber(number));
        callback.onChange();
    }


}
