package io.mochadwi.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Query

/**
 * Created by mochadwi on 2019-10-31
 * Copyright (c) 2019 dicoding. All rights reserved.
 */

@Dao
abstract class FavouriteDao : BaseDao<FavouriteEntity> {

    // TODO(mochadwi): 2019-11-01 AND tbl_favourite.name IS NULL
    @Query("SELECT * FROM tbl_favourite WHERE tbl_favourite.isDeleted IS NULL")
    abstract suspend fun getFavourites(): List<FavouriteEntity>

    @Query("UPDATE tbl_favourite SET isDeleted = 'true' WHERE id = :id")
    abstract suspend fun deleteFavouriteById(id: Int): Int
}