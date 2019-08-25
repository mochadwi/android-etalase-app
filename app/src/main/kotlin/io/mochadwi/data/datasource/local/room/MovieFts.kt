package io.mochadwi.data.datasource.local.room

import androidx.room.Entity
import androidx.room.Fts4

@Fts4(contentEntity = MovieEntity::class)
@Entity(tableName = "tbl_movie_fts")
data class MovieFts(
        val title: String = "", // at nam consequatur ea labore ea harum
        val body: String = "" // cupiditate quo est a modi nesciunt solutaipsa voluptas error itaque dicta inautem qui minus magnam et distinctio eumaccusamus ratione error aut
)