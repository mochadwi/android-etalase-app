package io.mochadwi.domain.post

import io.mochadwi.data.datasource.room.PostEntity
import io.mochadwi.data.datasource.webservice.response.post.PostResponse

data class PostModel(
        val userId: Int = 0, // 10
        val id: Int = 0, // 100
        val title: String = "", // at nam consequatur ea labore ea harum
        val body: String = "" // cupiditate quo est a modi nesciunt solutaipsa voluptas error itaque dicta inautem qui minus magnam et distinctio eumaccusamus ratione error aut
) {
    companion object {
        // TODO(mochamadiqbaldwicahyo): 2019-08-13 Don't do this on domain layer
        fun from(post: PostResponse) = with(post) {
            PostModel(userId, id, title, body)
        }

        fun from(post: PostEntity) = with(post) {
            PostModel(userId, id, title, body)
        }
    }
}