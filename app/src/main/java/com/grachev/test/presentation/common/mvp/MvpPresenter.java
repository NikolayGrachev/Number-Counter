package com.grachev.test.presentation.common.mvp;

/**
 * Created by Nick on 19.07.2017.
 */

public interface MvpPresenter <V> {

    void attachView(V view);

    void viewIsReady();

    void detachView();

    void destroy();
}