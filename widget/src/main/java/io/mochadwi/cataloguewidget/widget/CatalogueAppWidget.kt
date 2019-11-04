package io.mochadwi.cataloguewidget.widget

import android.annotation.TargetApi
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.RemoteViews
import android.widget.Toast

import io.mochadwi.cataloguewidget.R
import io.mochadwi.cataloguewidget.home.MainActivity
import io.mochadwi.cataloguewidget.services.StackWidgetService

/**
 * Implementation of App Widget functionality.
 */
class CatalogueAppWidget : AppWidgetProvider() {

    internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {

        val intent = Intent(context, StackWidgetService::class.java)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))

        val views = RemoteViews(context.packageName, R.layout.catalogue_widget)
        // Set up the collection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            setRemoteAdapter(context, views)
        } else {
            setRemoteAdapterV11(context, views)
        }

        views.setEmptyView(R.id.stack_view, R.id.empty_view)

        val toastIntent = Intent(context, MainActivity::class.java)
        toastIntent.action = TOAST_ACTION
        toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))
        val toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        views.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent)

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
            Toast.makeText(context, "Touched view $appWidgetId", Toast.LENGTH_SHORT).show()
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context, intent: Intent) {
        val mgr = AppWidgetManager.getInstance(context)
        if (intent.action == TOAST_ACTION) {
            val appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID)
            val viewIndex = intent.getIntExtra(EXTRA_ITEM, 0)
            Toast.makeText(context, "Touched view $viewIndex", Toast.LENGTH_SHORT).show()
        }
        super.onReceive(context, intent)
    }

    protected fun getPendingSelfIntent(context: Context, appWidgetId: Int, action: String): PendingIntent {
        val intent = Intent(context, javaClass)
        intent.action = action
        intent.putExtra(WIDGET_ID_EXTRA, appWidgetId)
        return PendingIntent.getBroadcast(context, appWidgetId, intent, 0)
    }

    companion object {

        val TOAST_ACTION = "io.mochadwi.cataloguewidget.TOAST_ACTION"
        val EXTRA_ITEM = "io.mochadwi.cataloguewidget.EXTRA_ITEM"
        val NOTIFICATION_ID = 100
        private val WIDGET_CLICK = "widgetsclick"
        private val WIDGET_ID_EXTRA = "widget_id_extra"

        /**
         * Sets the remote adapter used to fill in the list items
         *
         * @param views RemoteViews to set the RemoteAdapter
         */
        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        private fun setRemoteAdapter(context: Context, views: RemoteViews) {
            views.setRemoteAdapter(R.id.stack_view,
                    Intent(context, StackWidgetService::class.java))
        }

        /**
         * Sets the remote adapter used to fill in the list items
         *
         * @param views RemoteViews to set the RemoteAdapter
         */
        private fun setRemoteAdapterV11(context: Context, views: RemoteViews) {
            views.setRemoteAdapter(0, R.id.stack_view,
                    Intent(context, StackWidgetService::class.java))
        }
    }
}