package io.mochadwi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import io.mochadwi.R
import io.mochadwi.databinding.NotifSettingActivityBinding
import io.mochadwi.util.ext.isDailyNotifActive
import io.mochadwi.util.ext.isReleaseNotifActive
import io.mochadwi.util.ext.toastSpammable
import io.mochadwi.util.helper.AppHelper.Const.TAG_MOVIE_DAILY
import io.mochadwi.util.helper.AppHelper.Const.TAG_MOVIE_RELEASE
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

/**
 * Created by mochadwi on 2019-11-10
 * Copyright (c) 2019 dicoding. All rights reserved.
 */

class NotifSettingActivity : AppCompatActivity() {
    private val vb: NotifSettingActivityBinding by lazy {
        DataBindingUtil.setContentView<NotifSettingActivityBinding>(this, R.layout.notif_setting_activity)
    }
    private val workManager by inject<WorkManager>()
    private val releaseWorker by inject<PeriodicWorkRequest>(qualifier = named("release"))
    private val dailyWorker by inject<PeriodicWorkRequest>(qualifier = named("daily"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(vb.toolbar.tbCustom)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        vb.executePendingBindings()
        setupDailyCheckBox()
        setupReleaseCheckBox()
    }

    private fun setupDailyCheckBox() {
        vb.cbDaily.apply {
            isChecked = isDailyNotifActive
            setOnCheckedChangeListener { _, isChecked ->
                isDailyNotifActive = isChecked
                if (isDailyNotifActive) {
                    toastSpammable(getString(R.string.message_enable))
                    workManager.enqueue(dailyWorker)
                } else {
                    toastSpammable(getString(R.string.message_disable))
                    workManager.cancelAllWorkByTag(TAG_MOVIE_DAILY)
                }
            }
        }
    }

    private fun setupReleaseCheckBox() {
        vb.cbRelease.apply {
            isChecked = isReleaseNotifActive
            setOnCheckedChangeListener { _, isChecked ->
                isReleaseNotifActive = isChecked
                if (isReleaseNotifActive) {
                    toastSpammable(getString(R.string.message_enable))
                    workManager.enqueue(releaseWorker)
                } else {
                    toastSpammable(getString(R.string.message_disable))
                    workManager.cancelAllWorkByTag(TAG_MOVIE_RELEASE)
                }
            }
        }
    }
}