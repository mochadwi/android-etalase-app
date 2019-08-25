package io.mochadwi.domain.model.movie

import io.mochadwi.data.datasource.local.room.MovieEntity

data class MovieModel(
        val userId: Int = 0, // 10
        val id: Int = 0, // 100
        val title: String = "", // at nam consequatur ea labore ea harum
        val body: String = "" // cupiditate quo est a modi nesciunt solutaipsa voluptas error itaque dicta inautem qui minus magnam et distinctio eumaccusamus ratione error aut
) {
    companion object {
        // TODO(mochamadiqbaldwicahyo): 2019-08-13 Don't do this on domain layer
        fun from(movie: MovieResponse) = with(movie) {
            MovieModel(userId, id, title, body)
        }

        fun from(movie: MovieEntity) = with(movie) {
            MovieModel(userId, id, title, body)
        }
    }
}