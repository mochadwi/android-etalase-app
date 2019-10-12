package io.mochadwi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mochadwi.data.datasource.local.room.MovieDao
import io.mochadwi.util.mock.MockedData.mockMoviesEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */

/**
 * AppDaoTest is a KoinTest with AndroidJUnit4 runner
 *
 * KoinTest help inject Koin components from actual runtime
 */
@RunWith(AndroidJUnit4::class)
class AppDaoTest : KoinTest {

    /*
     * Inject needed components from Koin
     */
    private val movieDao: MovieDao by inject()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun test_insertMovieDao() = runBlocking {
        val result = movieDao.insert(mockMoviesEntity)
        result.forEach(::println)

        val movies = movieDao.getAllMovies()

        assertEquals(true, result.isNotEmpty())
        assertEquals(mockMoviesEntity.count(), movies.count())
        assertEquals(mockMoviesEntity.sortedBy { it.id }[0].id, movies[0].id)
    }

    @Test
    fun test_getAllMoviesDao() = runBlocking {
        val result = movieDao.getAllMovies()
        result.forEach(::println)

        assertEquals(true, result.isNotEmpty())
    }

    @Test
    fun test_searchMoviesDao() = runBlocking {
        val result = movieDao.searchMovies("a")
        result.forEach(::println)

        assertEquals(true, result.isNotEmpty())
    }
}