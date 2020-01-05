package io.mochadwi.di

import android.content.Context
import androidx.room.Room
import io.mochadwi.BuildConfig.BASE_URL
import io.mochadwi.MainApplication
import io.mochadwi.data.datasource.local.room.AppRoomDatabase
import io.mochadwi.data.datasource.network.RetrofitEndpoint
import io.mochadwi.data.repository.CoRepository
import io.mochadwi.domain.repository.AppRepository
import io.mochadwi.ui.movie.MovieViewModel
import io.mochadwi.util.TestSchedulerProvider
import io.mochadwi.util.mock
import io.mochadwi.util.rx.SchedulerProvider
import io.mochadwi.util.singleton
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val testAndroidModule =
        mock<MainApplication>().singleton(autoStart = true, override = true) +
                mock<Context>().singleton(autoStart = true, override = true)

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
    single { get<AppRoomDatabase>().movieDao() }
    single { get<AppRoomDatabase>().favouriteDao() }
}

/**
 * Test Rx
 */
val testRxModule = module {
    // provided components
    single { TestSchedulerProvider() as SchedulerProvider }
}

val testRepoModule = module {
    // App Data Repository
    single { CoRepository(get(), get(), get()) } bind AppRepository::class
}

val testViewModelModule = module {

    // ViewModel for Etalase app
    viewModel { MovieViewModel(get(), get()) }
}

val testOnlineEtalaseApp = testAndroidModule + testRemoteDatasourceModule + testRoomModule +
        testRxModule + testRepoModule + testViewModelModule