package io.mochadwi.data.datasource.mock.jsonreader

import io.mochadwi.data.datasource.network.kotlinx.response.movie.MovieResponse

/**
 * Json reader
 */
interface JsonReader {

    fun getMovies(name: String): List<MovieResponse>
}