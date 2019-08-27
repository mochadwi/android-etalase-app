package io.mochadwi.ui.movie

import androidx.lifecycle.LiveData
import io.mochadwi.domain.ErrorState
import io.mochadwi.domain.LoadingState
import io.mochadwi.domain.MovieListState
import io.mochadwi.domain.State
import io.mochadwi.domain.repository.AppRepository
import io.mochadwi.ui.movie.list.MovieItem
import io.mochadwi.util.base.BaseViewModel
import io.mochadwi.util.ext.toSingleEvent
import io.mochadwi.util.mvvm.LiveEvent
import io.mochadwi.util.mvvm.MutableSetObservableField
import io.mochadwi.util.rx.SchedulerProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */

class MovieViewModel(
    private val repo: AppRepository,
    schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider) {

    val keywords = Channel<String>(UNLIMITED)
    val movieListSet = MutableSetObservableField<MovieItem>()

    /*
     * We use LiveEvent to publish "states"
     * No need to publish and retain any view state
     */
    private val _states = LiveEvent<State>()
    val states: LiveData<State>
        get() = _states.toSingleEvent()

    fun getMovies() {
        _states.value = LoadingState

        launchIo {
            try {
                val movies = repo.getDiscoverMovies()

                _states.postValue(MovieListState.from(movies!!))
            } catch (error: Throwable) {
                _states.postValue(ErrorState(error))
            }
        }
    }

    fun searchMovies(query: String) {
        if (query.isNotBlank()) {
            _states.value = LoadingState

            launchIo {
                try {
                    val movies = repo.searchMovies(query)

                    _states.postValue(MovieListState.from(movies!!))
                } catch (error: Throwable) {
                    _states.postValue(ErrorState(error))
                }
            }
        }
    }
}