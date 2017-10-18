package com.grachev.test.presentation.settings;

import com.grachev.test.presentation.common.mvp.MvpPresenter;

/**
 * Created by Nick on 19.07.2017.
 */

public interface SettingsContract {

    interface View {

        void setIncrementText(String text);
        void setMaximumText(String text);

        String getIncrementText();
        String getMaximumText();

    }
    interface Presenter extends MvpPresenter<View> {
        void onClickResetNumber();
        void onIncrementChanged(String text);
        void onMaximumChanged(String text);
    }

    interface Model {

        void setIncrement(int increment);
        int getIncrement();

        void setMaximum(int maximum);
        int getMaximum();

        void resetNumber();


    }
}
