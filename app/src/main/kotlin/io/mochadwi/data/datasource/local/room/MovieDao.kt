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
abstract class MovieDao : BaseDao<MovieEntity> {

    @Query("SELECT * FROM tbl_movie")
    abstract suspend fun getAllMovies(): List<MovieEntity>

    @Query("""
        SELECT tbl_movie.* FROM tbl_movie_fts
        JOIN tbl_movie ON tbl_movie_fts.docId = id
        WHERE tbl_movie_fts MATCH :query
    """)
    abstract suspend fun searchMovies(query: String): List<MovieEntity>
}