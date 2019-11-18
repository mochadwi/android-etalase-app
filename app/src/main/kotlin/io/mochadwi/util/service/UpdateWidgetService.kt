package io.mochadwi.util.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.widget.RemoteViews

import io.mochadwi.cataloguewidget.R
import io.mochadwi.cataloguewidget.utils.NumberGenerator

class UpdateWidgetService : JobService() {
    override fun onStartJob(jobParameters: JobParameters): Boolean {
        val manager = AppWidgetManager.getInstance(this)

        val view = RemoteViews(packageName, R.layout.catalogue_widget)
        val theWidget = ComponentName(this, NumberGenerator::class.java!!)

        val lastUpdate = "Random: " + NumberGenerator.Generate(100)

        view.setTextViewText(R.id.empty_view, lastUpdate)
        manager.updateAppWidget(theWidget, view)

        jobFinished(jobParameters, false)

        return true
    }

    override fun onStopJob(jobParameters: JobParameters): Boolean {
        return false
    }
}
