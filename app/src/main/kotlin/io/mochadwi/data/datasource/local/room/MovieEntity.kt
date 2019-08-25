package io.mochadwi.data.datasource.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_movie")
data class MovieEntity(
        @PrimaryKey
        val id: Int = 0, // 1
        val userId: Int = 0, // 10
        val title: String = "", // at nam consequatur ea labore ea harum
        val body: String = "" // cupiditate quo est a modi nesciunt solutaipsa voluptas error itaque dicta inautem qui minus magnam et distinctio eumaccusamus ratione error aut
) {
    companion object {
        fun from(response: MovieResponse) = with(response) {
            MovieEntity(id, userId, title, body)
        }
    }
}