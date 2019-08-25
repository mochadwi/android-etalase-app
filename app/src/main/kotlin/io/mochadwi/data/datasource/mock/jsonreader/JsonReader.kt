package io.mochadwi.data.datasource.mock.jsonreader

/**
 * Json reader
 */
interface JsonReader {

    fun getMovies(name: String): List<MovieResponse>
}