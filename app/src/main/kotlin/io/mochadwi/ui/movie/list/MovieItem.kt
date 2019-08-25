package io.mochadwi.ui.movie.list

import android.os.Parcelable
import io.mochadwi.data.datasource.local.room.MovieEntity
import io.mochadwi.domain.model.movie.MovieModel
import kotlinx.android.parcel.Parcelize

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */

@Parcelize
data class MovieItem(
        val userId: Int = 0, // 10
        val id: Int = 0, // 100
        val title: String = "", // at nam consequatur ea labore ea harum
        val body: String = "" // cupiditate quo est a modi nesciunt solutaipsa voluptas error itaque dicta inautem qui minus magnam et distinctio eumaccusamus ratione error aut
) : Parcelable {
    companion object {
        fun from(model: MovieModel) = with(model) {
            MovieItem(userId, id, title, body)
        }

        fun from(entity: MovieEntity) = with(entity) {
            MovieItem(userId, id, title, body)
        }
    }
}