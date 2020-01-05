package io.mochadwi.mock.mvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.mochadwi.di.testOnlineEtalaseApp
import io.mochadwi.domain.ErrorState
import io.mochadwi.domain.LoadingState
import io.mochadwi.domain.MovieListState
import io.mochadwi.domain.State
import io.mochadwi.domain.repository.AppRepository
import io.mochadwi.ui.movie.MovieViewModel
import io.mochadwi.util.MockitoHelper.argumentCaptor
import io.mochadwi.util.TestSchedulerProvider
import io.mochadwi.util.mock.MockedData.mockMoviesModel
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build weather-app
 *
 */
class MovieViewModelMockTest : KoinTest {

    @Mock
    lateinit var repository: AppRepository

    val viewModel: MovieViewModel by lazy {
        MovieViewModel(repository, TestSchedulerProvider())
    }

    @Mock
    lateinit var statesView: Observer<State>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    val times = 3

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin { modules(testOnlineEtalaseApp) }
        viewModel.states.observeForever(statesView)
    }

    @After
    fun after() {
        stopKoin()
        viewModel.states.removeObserver(statesView)
    }

    @Test
    fun `test MovieViewModel getMovies Succeed`() = runBlockingTest {
        given(repository.getDiscoverMovies()).willReturn(mockMoviesModel)

        viewModel.getMovies()

        // setup ArgumentCaptor
        val arg = argumentCaptor<State>()
        // Here we expect $times calls on statesView.onChanged
        verify(statesView, times(2)).onChanged(arg.capture())

        val values = arg.allValues
        // Test obtained values in order
        assertEquals(2, values.size)
        assertEquals(LoadingState, values[0])
        assertEquals(MovieListState.from(mockMoviesModel), values[1])
    }

    @Test
    fun `test MovieViewModel getMovies Failed`() = runBlockingTest {
        val error = Throwable("Got an error")
        given(repository.getDiscoverMovies()).will { throw error }

        viewModel.getMovies()

        // setup ArgumentCaptor
        val arg = argumentCaptor<State>()
        // Here we expect 2 calls on statesView.onChanged
        verify(statesView, times(2)).onChanged(arg.capture())

        val values = arg.allValues
        // Test obtained values in order
        assertEquals(2, values.size)
        assertEquals(LoadingState, values[0])
        assertEquals(ErrorState(error), values[1])
    }
}
