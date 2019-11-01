package io.mochadwi.data.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */

@Database(entities = [MovieEntity::class, MovieFts::class, FavouriteEntity::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun favouriteDao(): FavouriteDao
}