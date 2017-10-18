package com.grachev.test.presentation.common.base_views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.grachev.test.app.dagger.app.TestApp;
import com.grachev.test.app.dagger.app.TestAppComponent;

/**
 * Created by nikolay on 21.08.17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(TestApp.get(this).getTestAppComponent());
    }

    protected abstract void setupComponent(TestAppComponent ISunlifeAppComponent);



}
