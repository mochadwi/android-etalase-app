package io.mochadwi.cataloguewidget.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.net.ConnectivityManager
import android.os.Build
import android.preference.PreferenceManager
import com.google.gson.Gson
import java.io.IOException
import java.nio.charset.Charset
import java.util.*

/**
 * Created by mochadwi on 8/7/19.
 */

class AppHelper {
    object Strings {

        /**
         * Use to load JSON Strings from assets directory
         * and process the character to be Strings ready to consume
         * also acts as dummy data getter
         *
         * @param context  the activity context
         * @param fileName the file to check on the assets directory
         * @return the actual JSON Strings
         */
        fun loadJSONFromAsset(context: Context, fileName: String): String? {
            val json: String

            // The actual algorithm here
            try {
                val inputStream = context.assets.open(fileName)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                val charset = Charset.forName("UTF-8")

                inputStream.read(buffer)
                inputStream.close()

                json = String(buffer, charset)
            } catch (ex: IOException) {

                ex.printStackTrace()

                return ex.message
            }

            return json
        }

        /**
         * Use to cast any POJO type (generic type) / dynamic casting
         * and process it as JSON Strings
         *
         * @param model
         * @param <T>
         * @return the actual JSON Strings
        </T> */
        fun <T> pojoToJsonString(model: T): String {
            return Gson().toJson(model)
        }
    }

    /**
     * Use the localizationactivity library instead of manual LocaleManager
     * I'm still don't know how to maintain the state to restart the ViewPager fragment properly
     */
    object Locales {
        private val SELECTED_LANGUAGE = "PREF_LOCALE"

        /**
         * Use to replace default locale
         *
         * @param c
         * @return Context of replaced locale
         */
        fun setLocale(c: Context): Context {
            return updateResources(c, getLanguage(c))
        }

        /**
         * Use to set new locale
         *
         * @param c
         * @param language
         * @return Context of new locale
         */
        fun setNewLocale(c: Context, language: String): Context {
            persistLanguage(c, language) // store the locale on SharedPrefs
            return updateResources(c, language)
        }

        /**
         * Use to obtain the current locale from SharedPrefs
         *
         * @param c
         * @return String of locale language
         */
        fun getLanguage(c: Context): String? {
            // TODO: Implement singleton for SharedPref?
            val preferences = PreferenceManager.getDefaultSharedPreferences(c)

            return preferences.getString(SELECTED_LANGUAGE, Locale.getDefault().language) // en: as default lang
        }

        /**
         * Use to persist the current locale to SharedPrefs
         *
         * @param c
         * @param language
         */
        @SuppressLint("ApplySharedPref")
        private fun persistLanguage(c: Context, language: String) {
            // TODO: Implement singleton for SharedPref?
            val prefs = PreferenceManager.getDefaultSharedPreferences(c)

            // when Activity / Fragment restart .commit() will finish its action instead of .apply()
            prefs.edit().putString(SELECTED_LANGUAGE, language).commit()
        }

        /**
         * Use to update any resources that match the updated localization
         * Support API level below 17 and above it
         *
         * @param context
         * @param language
         * @return Context of the updated localization
         */
        private fun updateResources(context: Context, language: String?): Context {
            var context = context
            val locale = Locale(language)
            Locale.setDefault(locale)

            val res = context.resources
            val config = Configuration(res.configuration)

            if (Build.VERSION.SDK_INT >= 17) {
                config.setLocale(locale)
                context = context.createConfigurationContext(config)
            } else {
                config.locale = locale
                res.updateConfiguration(config, res.displayMetrics)
            }

            return context
        }

        /**
         * Use to obtain the current locale on Locale type
         *
         * @param res
         * @return Locale
         */
        fun getLocale(res: Resources): Locale {
            val config = res.configuration
            return if (Build.VERSION.SDK_INT >= 24) config.locales.get(0) else config.locale
        }
    }

    object Connections {

        /**
         * Use to check internet connectivity available
         *
         * @param context
         * @return
         */
        fun isConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }

    class SharedPrefs {
        private val sharedPreferences: SharedPreferences? = null
    }
}
