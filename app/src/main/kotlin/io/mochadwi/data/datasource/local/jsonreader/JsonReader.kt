package io.mochadwi.data.datasource.local.jsonreader

import io.mochadwi.data.datasource.network.kotlinx.response.post.PostResponse

/**
 * Json reader
 */
interface JsonReader {

    fun getPosts(name: String): List<PostResponse>
}