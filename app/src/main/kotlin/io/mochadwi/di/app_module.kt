package io.mochadwi.di

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import io.mochadwi.data.datasource.local.room.AppRoomDatabase
import io.mochadwi.data.repository.CoRepository
import io.mochadwi.domain.repository.AppRepository
import io.mochadwi.ui.movie.MovieViewModel
import io.mochadwi.util.helper.AppHelper
import io.mochadwi.util.rx.ApplicationSchedulerProvider
import io.mochadwi.util.rx.SchedulerProvider
import io.mochadwi.util.service.NotifyDailyWorker
import io.mochadwi.util.service.NotifyReleaseWorker
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

/**
 * App Components
 */
val rxModule = module {
    // Rx Schedulers
    single { ApplicationSchedulerProvider() as SchedulerProvider }
}

val viewModelModule = module {

    // ViewModel for Etalase app
    viewModel { MovieViewModel(get(), get(), get()) }
}

val roomModule = module {
    // Room Database
    single {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // TODO: Do real migration anything here
            }
        }
        Room.databaseBuilder(androidApplication(), AppRoomDatabase::class.java, "db_app")
//                .addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration() // TODO: Don't use this on production, check room json scheme for migration
                .build()
    }

    // Expose Dao directly
    single { get<AppRoomDatabase>().movieDao() }
    single { get<AppRoomDatabase>().favouriteDao() }
}

val repoModule = module {
    // App Data Repository
    single { CoRepository(get(), get(), get()) } bind AppRepository::class
}

val workManagerModule = module {
    single { WorkManager.getInstance(get()) }

    single(named("daily")) {
        PeriodicWorkRequest.Builder(NotifyDailyWorker::class.java, 1, TimeUnit.HOURS)
                .addTag(AppHelper.Const.TAG_MOVIE_DAILY)
                .build()
    }

    single(named("release")) {
        PeriodicWorkRequest.Builder(NotifyReleaseWorker::class.java, 1, TimeUnit.HOURS)
                .addTag(AppHelper.Const.TAG_MOVIE_RELEASE)
                .build()
    }
}

// Gather all app modules
val onlineEtalaseApp = listOf(workManagerModule, rxModule, remoteDatasourceModule, roomModule, repoModule, viewModelModule)
val offlineEtalaseApp = listOf(workManagerModule, rxModule, localAndroidDatasourceModule, roomModule, repoModule, viewModelModule)