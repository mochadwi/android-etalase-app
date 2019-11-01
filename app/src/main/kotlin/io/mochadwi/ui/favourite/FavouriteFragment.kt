package io.mochadwi.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import io.mochadwi.R
import io.mochadwi.databinding.FavouriteFragmentBinding
import io.mochadwi.ui.movie.MovieFragment
import io.mochadwi.ui.tvshow.TvShowFragment
import io.mochadwi.util.base.ToolbarListener

/**
 * Created by mochadwi on 2019-11-01
 * Copyright (c) 2019 dicoding. All rights reserved.
 */

class FavouriteFragment : Fragment() {

    private lateinit var vb: FavouriteFragmentBinding

    companion object {
        private const val NUM_PAGES = 2
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        vb = FavouriteFragmentBinding.inflate(inflater, container, false).apply {
            pager.apply {
                adapter = ScreenSlidePagerAdapter(childFragmentManager)
                setPageTransformer(true, ZoomOutPageTransformer())
            }

            tabLayout.setupWithViewPager(pager)
        }

        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as ToolbarListener).updateTitleToolbar(
                newTitle = getString(R.string.favourite)
        )
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
            FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment = when (position) {
            0 -> MovieFragment.newInstance(true)
            else -> TvShowFragment.newInstance(true)
        }

        override fun getPageTitle(position: Int): CharSequence? = when (position) {
            0 -> getString(R.string.movie)
            else -> getString(R.string.tv)
        }
    }
}

private const val MIN_SCALE = 0.85f
private const val MIN_ALPHA = 0.5f

class ZoomOutPageTransformer : ViewPager.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 0f
                }
                position <= 1 -> { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    // Fade the page relative to its size.
                    alpha = (MIN_ALPHA +
                            (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 0f
                }
            }
        }
    }
}