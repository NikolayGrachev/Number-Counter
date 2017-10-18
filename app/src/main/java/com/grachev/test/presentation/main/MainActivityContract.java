package com.grachev.test.presentation.main;

import com.grachev.test.presentation.common.mvp.MvpPresenter;

/**
 * Created by Nick on 19.07.2017.
 */

public interface MainActivityContract {

    interface View {
        void setNavigationDrawer();
        void attachNumberFragment();
        void attachSettingsFragment();
    }
    interface Presenter extends MvpPresenter<View> {
        void viewIsReady(int fragmentId);
    }

}
