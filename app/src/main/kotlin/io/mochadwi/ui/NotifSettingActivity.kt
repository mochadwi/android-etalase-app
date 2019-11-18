package io.mochadwi.ui

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import io.mochadwi.R
import io.mochadwi.databinding.NotifSettingActivityBinding
import io.mochadwi.util.ext.isDailyNotifActive
import io.mochadwi.util.ext.isReleaseNotifActive
import io.mochadwi.util.ext.isUpdateWidgetActive
import io.mochadwi.util.ext.toastSpammable
import io.mochadwi.util.helper.AppHelper.Const.TAG_MOVIE_DAILY
import io.mochadwi.util.helper.AppHelper.Const.TAG_MOVIE_RELEASE
import io.mochadwi.util.service.UpdateWidgetService
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

    companion object {
        private const val JOB_ID = 100
        private const val SCHEDULE_OF_PERIOD = 86000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(vb.toolbar.tbCustom)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        vb.executePendingBindings()
        setupDailyCheckBox()
        setupReleaseCheckBox()
        setupUpdateWidgetCheckBox()
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

    private fun setupUpdateWidgetCheckBox() {
        vb.cbRelease.apply {
            isChecked = isUpdateWidgetActive
            setOnCheckedChangeListener { _, isChecked ->
                isUpdateWidgetActive = isChecked
                if (isUpdateWidgetActive) startJob() else cancelJob()
            }
        }
    }

    private fun startJob() {
        val mServiceComponent = ComponentName(this, UpdateWidgetService::class.java)
        val builder = JobInfo.Builder(JOB_ID, mServiceComponent)
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setPeriodic(900000) //15 menit
        } else {
            builder.setPeriodic(SCHEDULE_OF_PERIOD) //3 menit
        }
        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(builder.build())

        toastSpammable(getString(R.string.appwidget_service_started))
    }

    private fun cancelJob() {
        val tm = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        tm.cancel(JOB_ID)
        toastSpammable(getString(R.string.appwidget_service_cancelled))
    }
}