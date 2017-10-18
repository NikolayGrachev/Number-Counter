package com.grachev.test.data;

import com.grachev.test.app.dagger.injection.preferences.PreferencesManager;
import com.grachev.test.presentation.number.NumberContract;

import javax.inject.Inject;

/**
 * Created by Nick on 20.07.2017.
 */

public class NumberModel implements NumberContract.Model {

    // переменная для хранения текущего значения счетчика
    private int number;
    private int increment;
    private int maximum;

    private PreferencesManager preferences;

    @Inject
    public NumberModel (PreferencesManager preferences) {
        this.preferences = preferences;
    }

    @Override
    public int getNumberFromPreferences() {
        // начальная инициализация, читаем сохраненное значение из префс
        number = preferences.getNumber();

        // читаем настройки из префс
        increment = preferences.getIncrement();
        maximum = preferences.getMaximum();

        return number;
    }

    @Override
    public void storeNumberToPreferences() {
        // сохраняем в префс
        preferences.setNumber(number);
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public void incrementNumber(NumberChangesCallback callback) {
        // увеличиваем число на инкремент
        int number = getNumber() + increment;

        if (number>maximum) {

            // сброс числа на ноль, если превышен максимум
            number = 0;
        }

        setNumber(number);

        // сообщаем об изменениях в презентер
        callback.onChange();
    }


}
