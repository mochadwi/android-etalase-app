package io.mochadwi.data.datasource.mock.jsonreader

import io.mochadwi.util.ext.fromJson

/**
 * Common parts for Json reader
 */
abstract class BaseReader : JsonReader {

    private val json_file = ".json"

    override fun getMovies(name: String): List<MovieResponse> =
        readJsonFile("$name$json_file").fromJson(MovieResponse.serializer().list)

    abstract fun getAllFiles(): List<String>

    abstract fun readJsonFile(jsonFile: String): String
}