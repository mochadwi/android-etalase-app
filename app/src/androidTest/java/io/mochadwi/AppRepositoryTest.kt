package io.mochadwi

import io.mochadwi.data.mapper.FavouriteDataMapper
import io.mochadwi.domain.model.favourite.Favourite
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
    fun test_searchMoviesApi() {
        runBlocking {
            val result = repository.searchMovies("foo")
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

    @Test
    fun test_getTvApi() {
        // why we need this? to check, if there's error on the framework/library side.
        // let's see this. Im using kotlinx serialization.
        val result = repository.getTvShows()
        result?.forEach(::println)
    }

    @Test
    fun test_isNotEmptyTvApi() {
        val result = repository.getTvShows()

        assertEquals(true, result != null)
        assertEquals(true, result?.isNotEmpty().default)
    }

    @Test
    fun test_isNotEmptyFavourites() {
        val result = repository.getLocalFavourites {
            it.map { entity ->
                FavouriteDataMapper.from(entity)
            }
        }

        assertEquals(true, result != null)
        assertEquals(true, result?.isNotEmpty().default)
    }

    @Test
    fun test_addFavourite() {
        val isAdded = repository.addToLocalFavourite(Favourite.empty())

        assertEquals(true, isAdded)
    }

    @Test
    fun test_deleteFavourite() {
        val isDeleted = repository.deleteFromLocalFavouriteById(0)

        assertEquals(true, isDeleted)
    }
}