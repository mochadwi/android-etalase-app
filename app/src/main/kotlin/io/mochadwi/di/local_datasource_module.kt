package io.mochadwi.di

import io.mochadwi.data.datasource.local.jsonreader.AndroidJsonReader
import io.mochadwi.data.datasource.local.jsonreader.JsonReader
import io.mochadwi.data.datasource.local.jsonreader.LocalFileDataSource
import io.mochadwi.data.datasource.network.RetrofitEndpoint
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Local Json Files Datasource
 */
val localAndroidDatasourceModule = module {
    single { AndroidJsonReader(androidApplication()) as JsonReader }
    single { LocalFileDataSource(get(), true) as RetrofitEndpoint }
}