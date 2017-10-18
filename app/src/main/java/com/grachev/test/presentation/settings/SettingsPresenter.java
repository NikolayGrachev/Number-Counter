package com.grachev.test.presentation.settings;

import com.grachev.test.data.SettingsModel;
import com.grachev.test.presentation.common.mvp.PresenterBase;

import javax.inject.Inject;

/**
 * Created by Nick on 20.07.2017.
 */

public class SettingsPresenter
        extends PresenterBase<SettingsContract.View>
        implements SettingsContract.Presenter {

    private SettingsModel model;

    @Inject
    public SettingsPresenter(SettingsModel model) {
        this.model = model;
    }

    // первая инициализация текста
    @Override
    public void viewIsReady() {

        getView().setIncrementText(String.valueOf(model.getIncrement()));
        getView().setMaximumText(String.valueOf(model.getMaximum()));

    }


    @Override
    public void onClickResetNumber() {
        model.resetNumber();
    }

    @Override
    public void onIncrementChanged(String text) {
        model.setIncrement(Integer.parseInt(text));
    }

    @Override
    public void onMaximumChanged(String text) {

        int max = Integer.parseInt(text);
        model.setMaximum(max);
    }
}
