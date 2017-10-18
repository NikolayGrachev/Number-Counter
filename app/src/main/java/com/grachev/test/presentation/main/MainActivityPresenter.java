package com.grachev.test.presentation.main;

import com.grachev.test.presentation.common.mvp.PresenterBase;

import javax.inject.Inject;

/**
 * Created by Nick on 20.07.2017.
 */

public class MainActivityPresenter extends PresenterBase<MainActivityContract.View>
        implements MainActivityContract.Presenter {

    private MainActivityContract.View view;

    @Inject
    public MainActivityPresenter (MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void viewIsReady(int fragmentId) {
        switch (fragmentId) {
            case 0:
                getView().attachNumberFragment();
                break;
            case 1:
                getView().attachSettingsFragment();
                break;
        }

    }

    @Override
    public void viewIsReady() {

    }
}
