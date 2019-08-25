package io.mochadwi.util.binding

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import io.mochadwi.BuildConfig
import io.mochadwi.R
import io.mochadwi.util.ext.gone
import io.mochadwi.util.ext.loadImage
import io.mochadwi.util.helper.AppHelper.Const.ATTR_IMAGE_SOURCE
import io.mochadwi.util.helper.AppHelper.Const.BASE_IMAGE_URL_MOVIE_DB
import io.mochadwi.util.helper.AppHelper.Const.TAG_IMAGE_URL
import io.mochadwi.util.helper.GlideApp

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */

object ImageBinding {

    @SuppressLint("PrivateResource")
    @BindingAdapter(ATTR_IMAGE_SOURCE)
    @JvmStatic
    fun ImageView.setImageSource(imageUrl: String?) {
        try {
            if (BuildConfig.DEBUG) {
                Log.d(TAG_IMAGE_URL, imageUrl.toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        loadImage("$BASE_IMAGE_URL_MOVIE_DB$imageUrl")
    }

    @SuppressLint("PrivateResource")
    @BindingAdapter(ATTR_IMAGE_SOURCE)
    @JvmStatic
    fun ImageView.setImageSource(imageUrl: Uri?) {
        try {
            if (BuildConfig.DEBUG) {
                Log.d(TAG_IMAGE_URL, imageUrl.toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        loadImage(context, imageUrl)
    }

    @SuppressLint("PrivateResource")
    @BindingAdapter(ATTR_IMAGE_SOURCE)
    @JvmStatic
    fun ImageView.setImageSource(imageDrawable: Drawable?) {
        try {
            if (BuildConfig.DEBUG) {
                Log.d(TAG_IMAGE_URL, imageDrawable.toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        loadImage(context, imageDrawable)
    }

    @SuppressLint("PrivateResource")
    @BindingAdapter("image:imgDrawable", "imgUrl")
    @JvmStatic
    fun ImageView.setImageSource(img0: Drawable?, img1: String?) {
        try {
            if (BuildConfig.DEBUG) {
                Log.d(TAG_IMAGE_URL, "$img0 + $img1")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        GlideApp.with(this.context)
            .load(img0 ?: img1)
            .apply {
                transition(DrawableTransitionOptions.withCrossFade(600))
                RequestOptions()
                    .fallback(R.color.material_grey_300)
                error(R.mipmap.ic_launcher)
            }
            .into(this)
    }

    @BindingAdapter("image:status", requireAll = true)
    @JvmStatic
    fun ImageView.setSaleStatus(status: String) {
        if (status == "sold_out") loadImage(context,
            ContextCompat.getDrawable(context, R.drawable.ic_badge_soldout))
        else gone
    }
}