package io.mochadwi.di

import io.mochadwi.data.datasource.mock.jsonreader.AndroidJsonReader
import io.mochadwi.data.datasource.mock.jsonreader.JsonReader
import io.mochadwi.data.datasource.mock.jsonreader.LocalFileDataSource
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