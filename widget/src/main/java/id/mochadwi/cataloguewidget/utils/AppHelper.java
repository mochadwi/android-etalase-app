package io.mochadwi.cataloguewidget.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;

/**
 * Created by mochadwi on 8/7/19.
 */

public class AppHelper {
    public static class Strings {

        /**
         * Use to load JSON Strings from assets directory
         * and process the character to be Strings ready to consume
         * also acts as dummy data getter
         *
         * @param context  the activity context
         * @param fileName the file to check on the assets directory
         * @return the actual JSON Strings
         */
        public static String loadJSONFromAsset(Context context, String fileName) {
            String json;

            // The actual algorithm here
            try {
                InputStream inputStream = context.getAssets().open(fileName);
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                Charset charset = Charset.forName("UTF-8");

                inputStream.read(buffer);
                inputStream.close();

                json = new String(buffer, charset);
            } catch (IOException ex) {

                ex.printStackTrace();

                return ex.getMessage();
            }

            return json;
        }

        /**
         * Use to cast any POJO type (generic type) / dynamic casting
         * and process it as JSON Strings
         *
         * @param model
         * @param <T>
         * @return the actual JSON Strings
         */
        public static <T> String pojoToJsonString(T model) {
            return new Gson().toJson(model);
        }
    }

    /**
     * Use the localizationactivity library instead of manual LocaleManager
     * I'm still don't know how to maintain the state to restart the ViewPager fragment properly
     */
    public static class Locales {
        private static String SELECTED_LANGUAGE = "PREF_LOCALE";

        /**
         * Use to replace default locale
         *
         * @param c
         * @return Context of replaced locale
         */
        public static Context setLocale(Context c) {
            return updateResources(c, getLanguage(c));
        }

        /**
         * Use to set new locale
         *
         * @param c
         * @param language
         * @return Context of new locale
         */
        public static Context setNewLocale(Context c, String language) {
            persistLanguage(c, language); // store the locale on SharedPrefs
            return updateResources(c, language);
        }

        /**
         * Use to obtain the current locale from SharedPrefs
         *
         * @param c
         * @return String of locale language
         */
        public static String getLanguage(Context c) {
            // TODO: Implement singleton for SharedPref?
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(c);

            return preferences.getString(SELECTED_LANGUAGE, Locale.getDefault().getLanguage()); // en: as default lang
        }

        /**
         * Use to persist the current locale to SharedPrefs
         *
         * @param c
         * @param language
         */
        @SuppressLint("ApplySharedPref")
        private static void persistLanguage(Context c, String language) {
            // TODO: Implement singleton for SharedPref?
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);

            // when Activity / Fragment restart .commit() will finish its action instead of .apply()
            prefs.edit().putString(SELECTED_LANGUAGE, language).commit();
        }

        /**
         * Use to update any resources that match the updated localization
         * Support API level below 17 and above it
         *
         * @param context
         * @param language
         * @return Context of the updated localization
         */
        private static Context updateResources(Context context, String language) {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);

            Resources res = context.getResources();
            Configuration config = new Configuration(res.getConfiguration());

            if (Build.VERSION.SDK_INT >= 17) {
                config.setLocale(locale);
                context = context.createConfigurationContext(config);
            } else {
                config.locale = locale;
                res.updateConfiguration(config, res.getDisplayMetrics());
            }

            return context;
        }

        /**
         * Use to obtain the current locale on Locale type
         *
         * @param res
         * @return Locale
         */
        public static Locale getLocale(Resources res) {
            Configuration config = res.getConfiguration();
            return Build.VERSION.SDK_INT >= 24 ? config.getLocales().get(0) : config.locale;
        }
    }

    public static class Connections {

        /**
         * Use to check internet connectivity available
         *
         * @param context
         * @return
         */
        public static boolean isConnected(Context context) {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
    }

    public static class SharedPrefs {
        private SharedPreferences sharedPreferences;
    }
}
