package com.grachev.test.presentation.number;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grachev.test.R;
import com.grachev.test.app.dagger.injection.main.MainActivityComponent;
import com.grachev.test.app.dagger.injection.preferences.PreferencesManager;
import com.grachev.test.presentation.common.base_views.BaseFragment;

import javax.inject.Inject;


/**
 * Created by nikolay on 06.07.17.
 */

public class NumberFragment extends BaseFragment implements NumberContract.View {


    @Inject
    NumberPresenter presenter;

    private View v;
    private NumberContract.View view;


    private TextView numberText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v =  inflater.inflate(R.layout.fragment_number, null);
        view = this;

        numberText = (TextView) v.findViewById(R.id.number);

        // при нажатии на цифру сообщаем презентеру
        numberText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClick();
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
    public void setNumberText(String number) {
        // показываем значение
        numberText.setText(number);
        // сообщаем в презентер, что отображение текста закончилось
        presenter.textViewIsReady();
    }

    @Override
    public String getNumberText() {
        return numberText.getText().toString();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.storeNumber();
    }



    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
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