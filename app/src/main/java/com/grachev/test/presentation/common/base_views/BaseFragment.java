package com.grachev.test.presentation.common.base_views;

import android.support.v4.app.Fragment;

import com.grachev.test.app.dagger.app.HasComponent;


/**
 * Created by nikolay on 21.08.17.
 */

public abstract class BaseFragment extends Fragment {
    @SuppressWarnings("unchecked")
    protected <T> T getComponent(Class<T> componentType) {
        return componentType.cast(((HasComponent<T>)getActivity()).getComponent());
    }
}