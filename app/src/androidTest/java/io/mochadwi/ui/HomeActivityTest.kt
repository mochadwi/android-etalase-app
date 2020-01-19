package io.mochadwi.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mochadwi.R
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowActivity
import org.robolectric.shadows.ShadowIntent


/**
 * Created by mochadwi on 2020-01-19.
 * Copyright (c) 2020 sampingan.co.id. All rights reserved.
 */

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Test
    fun shouldOpenNotifActivity() {
        // given
        val scenario = ActivityScenario.launch(HomeActivity::class.java)
        scenario.onActivity {
            val shadowActivity: ShadowActivity = Shadows.shadowOf(it)
            val menuItem = shadowActivity.optionsMenu.findItem(R.id.actNotif)

            // then
            it.onOptionsItemSelected(menuItem)

            val notifIntent = shadowActivity.nextStartedActivity
            val shadowIntent: ShadowIntent = Shadows.shadowOf(notifIntent)

            // verify
            assertThat(shadowIntent.intentClass.name, equalTo(NotifSettingActivity::class.java.name))
        }
    }
}