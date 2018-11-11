package com.grachev.test.presentation.main;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.grachev.test.R;
import com.grachev.test.app.dagger.app.HasComponent;
import com.grachev.test.app.dagger.app.TestAppComponent;
import com.grachev.test.app.dagger.injection.main.DaggerMainActivityComponent;
import com.grachev.test.app.dagger.injection.main.MainActivityComponent;
import com.grachev.test.app.dagger.injection.main.MainActivityModule;
import com.grachev.test.presentation.common.base_views.BaseActivity;
import com.grachev.test.presentation.number.NumberFragment;
import com.grachev.test.presentation.settings.SettingsFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import javax.inject.Inject;

import static com.grachev.test.app.utils.Constants.BUNDLE_FRAGMENT;
import static com.grachev.test.app.utils.Constants.TAG_FRAGMENT_NUMBER;
import static com.grachev.test.app.utils.Constants.TAG_FRAGMENT_SETTINGS;

public class MainActivity extends BaseActivity implements MainActivityContract.View,
        HasComponent<MainActivityComponent> {

    @Inject
    MainActivityPresenter presenter;

    private Toolbar toolbar;
    private Drawer materialDrawer;
    private MainActivityComponent mainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // при смене ориенации экрана
        int fragmentId;
        if (savedInstanceState != null) {
            fragmentId = savedInstanceState.getInt(BUNDLE_FRAGMENT, 0);
            init(fragmentId);
        } else {
            init(0);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        android.support.v4.app.Fragment f = MainActivity.this.getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_NUMBER);
        if (f instanceof NumberFragment) {
            outState.putInt(BUNDLE_FRAGMENT,0);
        }

        android.support.v4.app.Fragment frag = MainActivity.this.getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_SETTINGS);
        if (frag instanceof SettingsFragment) {
            outState.putInt(BUNDLE_FRAGMENT,1);
        }



    }

    // инициализаци view
    private void init(int fragmentId) {

        setToolbar();
        setNavigationDrawer();

        // attach view to presenter
        presenter.attachView(this);

        // view is ready to work
        presenter.viewIsReady(fragmentId);
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public void setNavigationDrawer() {
        materialDrawer = new DrawerBuilder()
                .withActivity(MainActivity.this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withSliderBackgroundColorRes(R.color.colorPrimaryDark)

                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.main)
                                .withSelectedColorRes(R.color.colorPrimary)
                                .withSelectedTextColorRes(R.color.white)
                                .withTextColorRes(R.color.md_white_1000)
                                .withIdentifier(0),
                        new PrimaryDrawerItem()
                                .withName(R.string.settings)
                                .withSelectedColorRes(R.color.colorPrimary)
                                .withSelectedTextColorRes(R.color.white)
                                .withTextColorRes(R.color.md_white_1000)
                                .withIdentifier(1)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        switch ((int) drawerItem.getIdentifier()) {

                            case 0:
                                attachNumberFragment();

                                break;

                            case 1:
                                attachSettingsFragment();

                                break;
                        }


                        materialDrawer.closeDrawer();


                        return true;
                    }


                }).build();
    }

    @Override
    public void attachNumberFragment() {


        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            NumberFragment tasksFragment = new NumberFragment();

            fragmentTransaction
                    .replace(R.id.flContent,tasksFragment,TAG_FRAGMENT_NUMBER)
                    .commit();
        }

    }

    @Override
    public void attachSettingsFragment() {

        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            SettingsFragment fragment = new SettingsFragment();


            fragmentTransaction
                    .replace(R.id.flContent,fragment,TAG_FRAGMENT_SETTINGS)
                    .commit();
        }

    }


    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        if (isFinishing()) {
            presenter.destroy();
        }
    }

    @Override
    public MainActivityComponent getComponent() {
        return mainActivityComponent;
    }

    @Override
    protected void setupComponent(TestAppComponent appComponent) {

        // здесь подключаем модули в том числе и для фрагментов

        mainActivityComponent = DaggerMainActivityComponent.builder()
                .testAppComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build();
        mainActivityComponent.inject(this);
    }
}
