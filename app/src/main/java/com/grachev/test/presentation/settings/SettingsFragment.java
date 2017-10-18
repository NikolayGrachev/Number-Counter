package com.grachev.test.presentation.settings;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.grachev.test.R;
import com.grachev.test.app.dagger.injection.main.MainActivityComponent;
import com.grachev.test.app.dagger.injection.preferences.PreferencesManager;
import com.grachev.test.presentation.common.base_views.BaseFragment;

import javax.inject.Inject;


/**
 * Created by nikolay on 06.07.17.
 */

public class SettingsFragment extends BaseFragment implements SettingsContract.View {


    @Inject
    SettingsPresenter presenter;

    private View v;
    private SettingsContract.View view;

    private EditText incrementEdit;
    private EditText maximumEdit;
    private Button resetButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v =  inflater.inflate(R.layout.fragment_settings, null);
        view = this;

        // при изменениях сохраняем в префс
        incrementEdit = (EditText) v.findViewById(R.id.increment);
        incrementEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    presenter.onIncrementChanged(incrementEdit.getText().toString());
            }
        });


        // при изменениях сохраняем в префс
        maximumEdit = (EditText) v.findViewById(R.id.maximum);
        maximumEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    presenter.onMaximumChanged(maximumEdit.getText().toString());
            }
        });


        resetButton = (Button) v.findViewById(R.id.reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickResetNumber();
            }
        });




        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.attachView(view);
        presenter.viewIsReady();
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // dagger - берем компоненты у родительского активити
        this.getComponent(MainActivityComponent.class).inject(this);
    }


    @Override
    public void setIncrementText(String text) {
        incrementEdit.setText(text);
    }

    @Override
    public void setMaximumText(String text) {
        maximumEdit.setText(text);
    }

    @Override
    public String getIncrementText() {
        return null;
    }

    @Override
    public String getMaximumText() {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}