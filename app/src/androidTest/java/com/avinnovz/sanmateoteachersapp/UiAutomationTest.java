package com.avinnovz.sanmateoteachersapp;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.avinnovz.sanmateoteachersapp.screens.login.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by rsbulanon on 4/22/17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UiAutomationTest {

    private String username;
    private String password;

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setup() {
        this.username = "nedflanders";
        this.password = "qwerty";
    }

    @Test
    public void runTest() {
        //input username value in et_username field
        onView(withId(R.id.et_username)).perform(typeText(this.username), closeSoftKeyboard());

        //input invalid password value in et_password field
        onView(withId(R.id.et_password)).perform(typeText(this.password), closeSoftKeyboard());

    }

    @After
    public void tearDown() {

    }
}
