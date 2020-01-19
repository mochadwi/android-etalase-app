package io.mochadwi

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import io.mochadwi.ui.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by mochadwi on 2020-01-19.
 * Copyright (c) 2020 sampingan.co.id. All rights reserved.
 */

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Test
    fun shouldOpenNotifActivity() {
        // given
        val scenario = launchActivity<HomeActivity>()
        scenario.onActivity {
            // when
            onView(withId(R.id.actNotif)).perform(click())

            // then
        }
    }
}