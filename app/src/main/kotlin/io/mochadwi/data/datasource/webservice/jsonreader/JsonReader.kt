package io.mochadwi.data.datasource.webservice.jsonreader

import io.mochadwi.data.datasource.webservice.response.post.PostResponse

/**
 * Json reader
 */
interface JsonReader {
    fun getPosts(name: String): List<PostResponse>
}