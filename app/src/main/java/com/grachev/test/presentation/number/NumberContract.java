package com.grachev.test.presentation.number;

import com.grachev.test.presentation.common.mvp.MvpPresenter;

/**
 * Created by Nick on 19.07.2017.
 */

public interface NumberContract {

    interface View {
        void setNumberText(String number);
        String getNumberText();
    }
    interface Presenter extends MvpPresenter<View> {
        void onClick();
        void textViewIsReady();
        void storeNumber();
    }
    interface Model {
        void storeNumber();
        int getNumber();
        void setNumber(int number);

        void incrementNumber(NumberChangesCallback callback);

        interface NumberChangesCallback {
            void onChange();
        }
    }
}
