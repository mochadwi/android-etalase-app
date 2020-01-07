package io.mochadwi.ui.movie

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mochadwi.R
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

/**
 * Created by mochadwi on 2020-01-07.
 * Copyright (c) 2020 sampingan.co.id. All rights reserved.
 */

@RunWith(AndroidJUnit4::class)
class MovieDetailFragmentTest {

    @Mock
    lateinit var viewModel: MovieViewModel

    @Test
    fun movieDetail_isFavorite() {
        val scenario = launchFragmentInContainer<MovieDetailFragment>()

        onView(withId(R.id.actFavourite)).perform(click())
    }
}