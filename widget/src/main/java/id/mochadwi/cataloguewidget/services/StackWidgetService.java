package io.mochadwi.cataloguewidget.services;

import android.content.Intent;
import android.widget.RemoteViewsService;

import io.mochadwi.cataloguewidget.utils.StackRemoteViewsFactory;

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this, intent);
    }
}
