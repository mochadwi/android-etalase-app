package io.mochadwi.util.binding

import android.annotation.SuppressLint
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.mochadwi.databinding.MovieItemBinding
import io.mochadwi.ui.movie.list.MovieViewHolder
import io.mochadwi.util.ext.default
import io.mochadwi.util.ext.setupGridLayoutManager
import io.mochadwi.util.ext.setupLinearLayoutManager
import io.mochadwi.util.helper.GenericAdapter

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */

object ListBinding {
    @SuppressLint(value = ["PrivateResource", "UNCHECKED_CAST"])
    @BindingAdapter(value = ["list:isGrid",
        "list:spanCount",
        "list:orientation",
        "list:isReversed"], requireAll = false)
    @JvmStatic
    // TODO: Receive generic ViewDataBinding as args
    fun RecyclerView.initAdapter(isGrid: Boolean = false,
                                 spanCount: Int = 0,
                                 orientation: Int = 0,
                                 isReversed: Boolean = false) {
        try {
            if (isGrid) setupGridLayoutManager(spanCount, orientation, isReversed)
            else setupLinearLayoutManager(orientation, isReversed)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    @SuppressLint(value = ["PrivateResource", "UNCHECKED_CAST"])
    @BindingAdapter(value = ["list:layoutId", "list:viewType"], requireAll = false)
    @JvmStatic
    fun <DATA> RecyclerView.initViewHolder(layoutId: Int,
                                           viewType: Int?) {
        try {
            adapter = object : GenericAdapter<DATA>() {
                override fun getLayoutId(position: Int, obj: DATA): Int {
                    return layoutId
                }

                // TODO: Refactor to generic instead of using when condition
                override fun getViewHolder(viewBinding: ViewDataBinding): RecyclerView.ViewHolder {
                    return MovieViewHolder(viewBinding as MovieItemBinding)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint(value = ["PrivateResource", "UNCHECKED_CAST"])
    @BindingAdapter(value = ["list:items"], requireAll = false)
    @JvmStatic
    fun <DATA> RecyclerView.initData(items: List<DATA>?) {
        try {
            if (adapter is GenericAdapter<*>) {
                (adapter as GenericAdapter<DATA>).setData(items ?: emptyList())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint(value = ["PrivateResource", "UNCHECKED_CAST"])
    @BindingAdapter(value = ["list:items", "list:fromFavourite"], requireAll = false)
    @JvmStatic
    fun <DATA> RecyclerView.initData(items: Set<DATA>?, fromFavourite: Boolean?) {
        try {
            if (adapter is GenericAdapter<*>) {
                (adapter as GenericAdapter<DATA>).isFromFavourite(fromFavourite.default)
                (adapter as GenericAdapter<DATA>).setData(items?.toList() ?: emptyList())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}