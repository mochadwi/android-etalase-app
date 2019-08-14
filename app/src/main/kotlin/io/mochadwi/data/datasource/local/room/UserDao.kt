package io.mochadwi.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Query

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */

@Dao
abstract class UserDao : BaseDao<UserEntity> {
    @Query("SELECT * FROM tbl_user")
    abstract suspend fun getAllUsers(): List<UserEntity>
}