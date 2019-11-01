package io.mochadwi.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Query

/**
 * Created by mochadwi on 2019-10-31
 * Copyright (c) 2019 dicoding. All rights reserved.
 */

@Dao
abstract class FavouriteDao : BaseDao<FavouriteEntity> {

    @Query("SELECT * FROM tbl_favourite WHERE tbl_favourite.deletedAt = NULL")
    abstract suspend fun getFavourites(): List<FavouriteEntity>

    @Query("UPDATE tbl_favourite SET deletedAt = NULL WHERE id = :id")
    abstract suspend fun deleteFavouriteById(id: Int): Int
}