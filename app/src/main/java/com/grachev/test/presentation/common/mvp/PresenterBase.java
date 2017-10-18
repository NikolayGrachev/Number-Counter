package com.grachev.test.presentation.common.mvp;

/**
 * Created by Nick on 19.07.2017.
 */

public abstract class PresenterBase<T> implements MvpPresenter<T> {

    private T view;

    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public T getView() {
        return view;
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void destroy() {    }
}