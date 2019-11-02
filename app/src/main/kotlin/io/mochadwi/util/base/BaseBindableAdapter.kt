package io.mochadwi.util.base

import android.view.View

interface BaseBindableAdapter<in T> {
    fun setHeader(items: T) {}
    fun setData(items: List<T>) {}
    fun setFooter(items: T) {}
    fun bind(data: T) {}
    fun onClick(v: View, data: T, fromFavourite: Boolean) {}
}