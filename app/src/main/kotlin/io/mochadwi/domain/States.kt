package io.mochadwi.domain

import android.content.res.Resources
import io.mochadwi.R
import io.mochadwi.domain.model.favourite.Favourite
import io.mochadwi.domain.model.movie.Movie

/**
 * Abstract State
 */
sealed class State

/**
 * Generic Loading State
 */
object LoadingState : State()

/**
 * Generic Error state
 * @param error - caught error
 */
data class ErrorState(val error: Throwable) : State()

data class MovieListState(
    val list: List<Movie>
) : State() {
    companion object {
        fun from(list: List<Movie>): MovieListState {
            return with(list) {
                when {
                    isEmpty() -> error(Resources.getSystem().getString(R.string.error_emptysearch))
                    else -> MovieListState(this)
                }
            }
        }
    }
}

data class FavouriteState(
        val isFavourite: Boolean
) : State()

data class FavouriteListState(
        val list: List<Favourite>
) : State() {
    companion object {
        fun from(list: List<Favourite>): FavouriteListState {
            return with(list) {
                when {
                    isEmpty() -> error(Resources.getSystem().getString(R.string.error_emptysearch))
                    else -> FavouriteListState(this)
                }
            }
        }
    }
}