package io.mochadwi.data.datasource.local.realm

import androidx.room.RoomDatabase

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */

// TODO(mochamadiqbaldwicahyo): 2019-08-15 This only a replica to switching framework
abstract class AppRealmDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
}