package io.mochadwi.cataloguefavourite

import android.content.Context
import android.content.res.Configuration
import android.support.multidex.MultiDexApplication

import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate
import com.facebook.stetho.Stetho

/**
 * Created by mochadwi on 8/6/18.
 */

class CatalogueApplication : MultiDexApplication() {
    private val TAG = this.javaClass.canonicalName // for logging purpose

    // Implement the localization libs here, using delegate method (from their README.md)
    internal var localizationDelegate = LocalizationApplicationDelegate(this)

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
    }

    /**
     * Use to attach the base context
     * And set the locale
     *
     * @param base
     */
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(localizationDelegate.attachBaseContext(base))
    }

    /**
     * Use to listen for configuration changes
     * Orientation, localization, etc
     *
     * @param newConfig
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localizationDelegate.onConfigurationChanged(this)
    }

    /**
     * Use to return this application context from Localization libs
     *
     * @return Context of localization libs
     */
    override fun getApplicationContext(): Context {
        return localizationDelegate.getApplicationContext(super.getApplicationContext())
    }
}
