package io.mochadwi.domain

import io.mochadwi.domain.model.movie.MovieModel

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
    val list: List<MovieModel>
) : State() {
    companion object {
        fun from(list: List<MovieModel>): MovieListState {
            return with(list) {
                when {
                    // TODO: @mochadwi Move this into strings instead
                    isEmpty() -> error("There's an empty movie instead, please check your keyword")
                    else -> MovieListState(this)
                }
            }
        }
    }
}
