package io.mochadwi.util.binding

import android.annotation.SuppressLint
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import io.mochadwi.util.ext.*


/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 01/03/19
 * dedicated to build etalase-app
 *
 */

object CardViewBinding {
    @SuppressLint(value = ["PrivateResource", "UNCHECKED_CAST"])
    @BindingAdapter(value = ["card:isError", "card:isProgress", "card:list"], requireAll = false)
    @JvmStatic
    fun <DATA> CardView.isVisible(isError: Boolean?, isProgress: Boolean?, list: Set<DATA>?) {
        if (list.isNullOrEmpty()) {
            gone
            return
        } else visible

        if (!isError.default && !isProgress.default) {
            visible
            slideTop()
        } else {
            gone
            stopAnim()
        }
    }
}