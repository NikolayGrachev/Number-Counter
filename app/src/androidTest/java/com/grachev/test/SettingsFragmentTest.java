package com.grachev.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentTransaction;

import com.grachev.test.app.dagger.injection.preferences.PreferencesManager;
import com.grachev.test.presentation.main.MainActivity;
import com.grachev.test.presentation.number.NumberFragment;
import com.grachev.test.presentation.settings.SettingsFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.grachev.test.app.dagger.injection.preferences.PreferencesManager.FILE_NAME;
import static com.grachev.test.app.utils.Constants.TAG_FRAGMENT_NUMBER;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class SettingsFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init(){
        FragmentTransaction fragmentTransaction = activityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
        SettingsFragment fragment = new SettingsFragment();

        fragmentTransaction
                .replace(R.id.flContent, fragment, TAG_FRAGMENT_NUMBER)
                .commit();
    }

    @Test
    public void checkWrongIncrement() {
        // очищаем поле, после чего вводим неправильный символ
        // (тест не проходит, в коде специально нет проверки на цифровое значение)
        onView(withId(R.id.increment))
                .perform(clearText());

        onView(withId(R.id.increment))
                .perform(typeText("-"));
    }


    // нажатие на обнулить счетчик стирает из префс
    private PreferencesManager preferencesManager;

    @Before
    public void setPref() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        SharedPreferences preferences = appContext.getSharedPreferences(FILE_NAME, 0);
        this.preferencesManager = new PreferencesManager(preferences);
    }

    @Test
    public void checkResetCounter() {
        // очищаем поле, после чего вводим неправильный символ
        // (тест не проходит, в коде специально нет проверки на цифровое значение)
        onView(withId(R.id.reset))
                .perform(click());

        assertEquals(0, preferencesManager.getNumber());
    }
}
