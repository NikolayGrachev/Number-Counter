package com.grachev.test.app.dagger.injection.main;

import com.grachev.test.app.dagger.injection.preferences.PreferencesManager;
import com.grachev.test.presentation.main.MainActivityContract;
import com.grachev.test.presentation.main.MainActivityPresenter;
import com.grachev.test.data.NumberModel;
import com.grachev.test.presentation.number.NumberPresenter;
import com.grachev.test.data.SettingsModel;
import com.grachev.test.presentation.settings.SettingsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nikolay on 21.08.17.
 */

@Module
public class MainActivityModule {

    private MainActivityContract.View view;

    public MainActivityModule(MainActivityContract.View view) {
        this.view = view;
    }

    @Provides
    public MainActivityContract.View provideView() {
        return view;
    }


    @Provides
    public MainActivityPresenter provideMainActivityPresenter (MainActivityContract.View view){
        return  new MainActivityPresenter(view);
    }

    @Provides
    public SettingsModel provideSettingsModel(PreferencesManager preferencesManager) {
        return new SettingsModel(preferencesManager);
    }

    @Provides
    public SettingsPresenter provideSettingsPresenter(SettingsModel model) {
        return new SettingsPresenter(model);
    }

    @Provides
    public NumberModel provideNumberModel(PreferencesManager preferencesManager) {
        return new NumberModel(preferencesManager);
    }

    @Provides
    public NumberPresenter provideNumbersPresenter(NumberModel model) {
        return new NumberPresenter(model);
    }


}
