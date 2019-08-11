package io.mochadwi.util.ext

import android.view.View

/**
 *
 * In syaa Allah created or modified by @mochadwi
 * On 10/08/19 for etalase-app
 */

val View.visible: View
    get() = apply {
        visibility = View.VISIBLE
    }

val View.gone: View
    get() = apply {
        visibility = View.GONE
    }

val View.invisible: View
    get() = apply {
        visibility = View.INVISIBLE
    }

val View.isVisible: Boolean
    get() = visibility == View.VISIBLE

val View.isInvisible: Boolean
    get() = visibility == View.INVISIBLE

val View.isGone: Boolean
    get() = visibility == View.GONE