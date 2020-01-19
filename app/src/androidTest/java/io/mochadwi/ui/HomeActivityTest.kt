package io.mochadwi.ui

import io.mochadwi.R
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowActivity
import org.robolectric.shadows.ShadowIntent


/**
 * Created by mochadwi on 2020-01-19.
 * Copyright (c) 2020 sampingan.co.id. All rights reserved.
 */

@RunWith(RobolectricTestRunner::class)
class HomeActivityTest {
    @Test
    fun shouldOpenNotifActivity() {
        // given
        val activity: HomeActivity = Robolectric.buildActivity(HomeActivity::class.java).create().visible().get()
        val shadowActivity: ShadowActivity = Shadows.shadowOf(activity)
        val menuItem = shadowActivity.optionsMenu.findItem(R.id.actNotif)

        // then
        activity.onOptionsItemSelected(menuItem)

        val notifIntent = shadowActivity.nextStartedActivity
        val shadowIntent: ShadowIntent = Shadows.shadowOf(notifIntent)

        // verify
        assertThat(shadowIntent.intentClass.name, equalTo(NotifSettingActivity::class.java.name))
    }
}