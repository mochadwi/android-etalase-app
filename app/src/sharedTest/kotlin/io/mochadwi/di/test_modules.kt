package io.mochadwi.di

import android.content.Context
import androidx.room.Room
import io.mochadwi.BuildConfig.BASE_URL
import io.mochadwi.MainApplication
import io.mochadwi.data.datasource.local.jsonreader.JavaReader
import io.mochadwi.data.datasource.local.jsonreader.JsonReader
import io.mochadwi.data.datasource.local.jsonreader.LocalFileDataSource
import io.mochadwi.data.datasource.local.room.AppRoomDatabase
import io.mochadwi.data.datasource.network.RetrofitEndpoint
import io.mochadwi.util.TestSchedulerProvider
import io.mochadwi.util.mock
import io.mochadwi.util.rx.SchedulerProvider
import io.mochadwi.util.singleton
import org.koin.dsl.bind
import org.koin.dsl.module

val testAndroidModule =
        mock<MainApplication>().singleton(autoStart = true, override = true) +
                mock<Context>().singleton(autoStart = true, override = true)

/**
 * Local java json repository
 */
val testLocalJavaDatasourceModule = module {
    single { JavaReader() } bind JsonReader::class
    single { LocalFileDataSource(get(), false) as RetrofitEndpoint }
}

/**
 * Remote Web Service datasource
 */
val testRemoteDatasourceModule = module {
    single { createOkHttpClient() }

    // Fill property
    single { createWebService<RetrofitEndpoint>(get(), BASE_URL) }
}

/**
 * In-Memory Room Database definition for test purpose
 */
val testRoomModule = module {
    single {
        Room.inMemoryDatabaseBuilder(get(), AppRoomDatabase::class.java)
                .allowMainThreadQueries() // for test purpose
                .fallbackToDestructiveMigration()
                .build()
    }

    // Expose Dao directly
    single { get<AppRoomDatabase>().userDao() }
    single { get<AppRoomDatabase>().postDao() }
}

/**
 * Test Rx
 */
val testRxModule = module {
    // provided components
    single { TestSchedulerProvider() as SchedulerProvider }
}

val testOnlineEtalaseApp = testAndroidModule + testRemoteDatasourceModule + testRoomModule +
        testRxModule + repoModule + viewModelModule
val testOfflineEtalaseApp = testAndroidModule + testLocalJavaDatasourceModule + testRoomModule +
        testRxModule + repoModule + viewModelModule
