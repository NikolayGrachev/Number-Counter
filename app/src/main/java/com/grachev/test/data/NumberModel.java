package com.grachev.test.data;

import com.grachev.test.app.dagger.injection.preferences.PreferencesManager;
import com.grachev.test.data.incrementer.Incrementer;
import com.grachev.test.presentation.number.NumberContract;

import javax.inject.Inject;

/**
 * Created by Nick on 20.07.2017.
 */

public class NumberModel implements NumberContract.Model {

    // переменная для хранения текущего значения счетчика
    private int number;
    private PreferencesManager preferences;
    private Incrementer incrementer;

    @Inject
    public NumberModel (PreferencesManager preferences) {
        this.preferences = preferences;

        init();
    }

    private void init() {
        // начальная инициализация, читаем сохраненное значение из префс
        number = preferences.getNumber();

        // читаем настройки из префс
        incrementer = new Incrementer(preferences.getIncrement(), preferences.getMaximum());
    }

    @Override
    public void storeNumber() {
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
        setNumber(incrementer.calcNumber(number));

        // сообщаем об изменениях в презентер
        callback.onChange();
    }


}
