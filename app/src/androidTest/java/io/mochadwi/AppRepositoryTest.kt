package io.mochadwi

import io.mochadwi.domain.repository.AppRepository
import io.mochadwi.util.ext.default
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

/**
 *
 * In syaa Allah created or modified by @mochadwi
 * On 2019-05-20 for etalase-app
 */

class AppRepositoryTest : KoinTest {
    private val repository by inject<AppRepository>()

    @Test
    fun test_searchPostsApi() {
        runBlocking {
            val result = repository.searchPostsAsync("foo").await()
            result?.forEach(::println)
        }
    }

    @Test
    fun test_getProductsApi() {
        val result = repository.getDiscoverMovies()
        result?.forEach(::println)
    }

    @Test
    fun test_isNotEmptyProductsApi() {
        val result = repository.getDiscoverMovies()

        assertEquals(true, result != null)
        assertEquals(true, result?.isNotEmpty().default)
    }
}