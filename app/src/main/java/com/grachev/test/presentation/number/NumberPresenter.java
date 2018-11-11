package com.grachev.test.presentation.number;

import com.grachev.test.data.NumberModel;
import com.grachev.test.presentation.common.mvp.PresenterBase;

import javax.inject.Inject;

/**
 * Created by Nick on 20.07.2017.
 */

public class NumberPresenter extends PresenterBase<NumberContract.View>
        implements NumberContract.Presenter {



    private NumberModel model;
    private boolean isTextViewReady = false;

    @Inject
    public NumberPresenter(NumberModel model) {
        this.model = model;
    }

    // первая инициализация текста
    @Override
    public void viewIsReady() {
        // инициализируем  начальное значение текста
        setNumberText();
    }

    private void setNumberText () {
        getView().setNumberText(String.valueOf(model.getNumber()));
    }

    @Override
    public void textViewIsReady() {
        isTextViewReady = true;
    }

    @Override
    public void onClick() {
        // при нажатии на кнопку увеличиваем число на размер инкремента и ждем колбэка
        model.incrementNumber(new NumberModel.NumberChangesCallback() {
            @Override
            public void onChange() {
                updateNumber();

            }
        });
    }

    private void updateNumber() {
        // вью будет перерисовано, если оно доступно
        if (isTextViewReady) {
            isTextViewReady = false;
            // обновляем textView, если число изменилось
            setNumberText();
        }

    }

    @Override
    public void storeNumber() {
        // завершение
        model.storeNumber();
    }


}
