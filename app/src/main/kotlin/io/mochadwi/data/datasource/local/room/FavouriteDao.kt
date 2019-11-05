package io.mochadwi.data.datasource.local.room

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Query

/**
 * Created by mochadwi on 2019-10-31
 * Copyright (c) 2019 dicoding. All rights reserved.
 */

@Dao
abstract class FavouriteDao : BaseDao<FavouriteEntity> {

    // TODO(mochadwi): 2019-11-01 AND tbl_favourite.name IS NULL
    @Query("SELECT * FROM tbl_favourite WHERE isFavourite = 1")
    abstract suspend fun getFavourites(): List<FavouriteEntity>

    @Query("SELECT * FROM tbl_favourite WHERE id = :id")
    abstract suspend fun getFavouriteById(id: Int): FavouriteEntity

    @Query("UPDATE tbl_favourite SET isFavourite = 0 WHERE id = :id")
    abstract suspend fun deleteFavouriteById(id: Long): Int

    @Query("SELECT * FROM tbl_favourite WHERE isFavourite = 1")
    abstract suspend fun selectAll(): Cursor

    @Query("SELECT * FROM tbl_favourite WHERE id = :id")
    abstract suspend fun selectById(id: Long): Cursor
}