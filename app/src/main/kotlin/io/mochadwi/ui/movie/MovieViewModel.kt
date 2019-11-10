package io.mochadwi.ui.movie

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.mochadwi.domain.*
import io.mochadwi.domain.repository.AppRepository
import io.mochadwi.ui.favourite.item.FavouriteItem
import io.mochadwi.ui.favourite.mapper.FavouriteItemMapper
import io.mochadwi.ui.movie.list.MovieItem
import io.mochadwi.util.base.BaseViewModel
import io.mochadwi.util.ext.default
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
        schedulerProvider: SchedulerProvider,
        private val context: Context
) : BaseViewModel(schedulerProvider) {

    val keywords = Channel<String>(UNLIMITED)
    val movieListSet = MutableSetObservableField<MovieItem>()
    val isMovieFavourite = ObservableField<Boolean>()

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
                val tv = repo.getTvShows()!!

                _states.postValue(MovieListState.from(tv))
            } catch (error: Throwable) {
                _states.postValue(ErrorState(error))
            }
        }
    }

    fun getMovies() {
        _states.value = LoadingState

        launchIo {
            try {
                val movies = repo.getDiscoverMovies()!!

                _states.postValue(MovieListState.from(movies))
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

    fun getMovieById(id: Int) {
        _states.value = LoadingState

        launchIo {
            try {
                val movie = repo.getLocalMovieById(id)!!

                _states.postValue(FavouriteListState(movie))
            } catch (error: Throwable) {
                _states.postValue(ErrorState(error))
            }
        }
    }

    fun getMovieFavourites() {
        _states.value = LoadingState

        launchIo {
            try {
                val movieFavourites = repo.getLocalFavouriteMovies()

                _states.postValue(FavouriteListState(list = movieFavourites!!))
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

                _states.postValue(FavouriteListState(list = movieFavourites!!))
            } catch (error: Throwable) {
                _states.postValue(ErrorState(error))
            }
        }
    }

    fun addToContentProvider(uri: Uri, contentValues: ContentValues) {
        launchIo {
            try {
                isMovieFavourite.set(
                        context.contentResolver.insert(uri, contentValues) != null
                )

                _states.postValue(FavouriteState(isMovieFavourite.get().default))
            } catch (error: Throwable) {
                _states.postValue(ErrorState(error))
            }
        }
    }

    fun addToFavourite(data: FavouriteItem) {
        launchIo {
            try {
                isMovieFavourite.set(repo.addToLocalFavourite(
                        FavouriteItemMapper.from(data.copy(isFavourite = true))
                ))

                _states.postValue(FavouriteState(isMovieFavourite.get().default))
            } catch (error: Throwable) {
                _states.postValue(ErrorState(error))
            }
        }
    }

    fun deleteFromFavourite(id: Int) {
        launchIo {
            try {
                val isDeleted = repo.deleteFromLocalFavouriteById(id)

                isMovieFavourite.set(!isDeleted)

                _states.postValue(FavouriteState(isMovieFavourite.get().default))
            } catch (error: Throwable) {
                _states.postValue(ErrorState(error))
            }
        }
    }
}