package io.mochadwi.cataloguewidget.services

import android.content.Intent
import android.widget.RemoteViewsService

import io.mochadwi.cataloguewidget.utils.StackRemoteViewsFactory

class StackWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsService.RemoteViewsFactory {
        return StackRemoteViewsFactory(this, intent)
    }
}
