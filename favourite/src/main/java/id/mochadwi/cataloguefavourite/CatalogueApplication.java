package id.mochadwi.cataloguefavourite;

import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDexApplication;

import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate;
import com.facebook.stetho.Stetho;

/**
 * Created by mochadwi on 8/6/18.
 */

public class CatalogueApplication extends MultiDexApplication {
    private final String TAG = this.getClass().getCanonicalName(); // for logging purpose

    // Implement the localization libs here, using delegate method (from their README.md)
    LocalizationApplicationDelegate localizationDelegate = new LocalizationApplicationDelegate(this);

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
    }

    /**
     * Use to attach the base context
     * And set the locale
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(localizationDelegate.attachBaseContext(base));
    }

    /**
     * Use to listen for configuration changes
     * Orientation, localization, etc
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        localizationDelegate.onConfigurationChanged(this);
    }

    /**
     * Use to return this application context from Localization libs
     *
     * @return Context of localization libs
     */
    @Override
    public Context getApplicationContext() {
        return localizationDelegate.getApplicationContext(super.getApplicationContext());
    }
}
