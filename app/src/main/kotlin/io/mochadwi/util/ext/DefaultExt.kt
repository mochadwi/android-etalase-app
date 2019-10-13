package io.mochadwi.util.ext

import io.mochadwi.ui.movie.list.MovieItem

/**
 *
 * In syaa Allah created or modified by @mochadwi
 * On 10/08/19 for etalase-app
 */

val Boolean?.default: Boolean
    get() = this ?: false

val Int?.default: Int
    get() = this ?: 0

val Double?.default: Double
    get() = this ?: 0.0

val Float?.default: Float
    get() = this ?: 0F

val String?.default: String
    get() = this ?: ""

val <T> ArrayList<T>?.default: ArrayList<T>
    get() = this ?: arrayListOf()

val <T> List<T>?.default: List<T>
    get() = this ?: listOf()

val MovieItem?.default: MovieItem
    get() = this ?: MovieItem(id = 0, title = "", name = "")