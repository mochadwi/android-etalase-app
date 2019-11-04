package io.mochadwi.cataloguewidget.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.widget.RemoteViews;

import io.mochadwi.cataloguewidget.R;
import io.mochadwi.cataloguewidget.utils.NumberGenerator;

public class UpdateWidgetService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        AppWidgetManager manager = AppWidgetManager.getInstance(this);

        RemoteViews view = new RemoteViews(getPackageName(), R.layout.catalogue_widget);
        ComponentName theWidget = new ComponentName(this, NumberGenerator.class);

        String lastUpdate = "Random: " + NumberGenerator.Generate(100);

        view.setTextViewText(R.id.empty_view, lastUpdate);
        manager.updateAppWidget(theWidget, view);

        jobFinished(jobParameters, false);

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
