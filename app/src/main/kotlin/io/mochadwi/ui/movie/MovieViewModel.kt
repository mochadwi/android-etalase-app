package io.mochadwi.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.mochadwi.domain.*
import io.mochadwi.domain.repository.AppRepository
import io.mochadwi.ui.favourite.item.FavouriteItem
import io.mochadwi.ui.favourite.mapper.FavouriteItemMapper
import io.mochadwi.ui.movie.list.MovieItem
import io.mochadwi.util.base.BaseViewModel
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
    var isMovieFavourite = false

    /*
     * We use LiveEvent to publish "states"
     * No need to publish and retain any view state
     */
    private val _states = MutableLiveData<State>()
    val states: LiveData<State>
        get() = _states

    fun getTvShows() {
        _states.value = LoadingState

        launchIo {
            try {
                val movies = repo.getTvShows()

                _states.postValue(MovieListState.from(movies!!))
            } catch (error: Throwable) {
                _states.postValue(ErrorState(error))
            }
        }
    }

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

    fun getMovieFavourites() {
        _states.value = LoadingState

        launchIo {
            try {
                val movieFavourites = repo.getLocalFavouriteMovies()

                _states.postValue(FavouriteListState(movieFavourites ?: emptyList()))
            } catch (error: Throwable) {
                _states.postValue(ErrorState(error))
            }
        }
    }

    fun getTvFavourites() {
        _states.value = LoadingState

        launchIo {
            try {
                val movieFavourites = repo.getLocalFavouriteTv()

                _states.postValue(FavouriteListState(movieFavourites ?: emptyList()))
            } catch (error: Throwable) {
                _states.postValue(ErrorState(error))
            }
        }
    }

    fun addToFavourite(data: FavouriteItem) {
        launchIo {
            try {
                isMovieFavourite = repo.addToLocalFavourite(FavouriteItemMapper.from(data))

                _states.postValue(FavouriteState(isMovieFavourite))
            } catch (error: Throwable) {
                _states.postValue(ErrorState(error))
            }
        }
    }

    fun deleteFromFavourite(id: Int) {
        launchIo {
            try {
                val isDeleted = repo.deleteFromLocalFavouriteById(id)

                isMovieFavourite = !isDeleted

                _states.postValue(FavouriteState(isMovieFavourite))
            } catch (error: Throwable) {
                _states.postValue(ErrorState(error))
            }
        }
    }
}