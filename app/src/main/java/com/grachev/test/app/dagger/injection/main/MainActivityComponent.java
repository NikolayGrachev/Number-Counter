package com.grachev.test.app.dagger.injection.main;

import com.grachev.test.app.dagger.app.ActivityScope;
import com.grachev.test.app.dagger.app.TestAppComponent;
import com.grachev.test.presentation.main.MainActivity;
import com.grachev.test.presentation.number.NumberFragment;
import com.grachev.test.presentation.settings.SettingsFragment;

import dagger.Component;


/**
 * Created by nikolay on 21.08.17.
 */

@ActivityScope
@Component(
        dependencies = TestAppComponent.class,
        modules = {MainActivityModule.class,}
)
public interface MainActivityComponent {
    void inject(MainActivity activity);
    void inject(NumberFragment numberFragment);
    void inject(SettingsFragment categoryFragment);
}
