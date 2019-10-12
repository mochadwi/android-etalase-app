package io.mochadwi.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import io.mochadwi.databinding.MoviedetailFragmentBinding
import io.mochadwi.util.base.ToolbarListener
import io.mochadwi.util.ext.default

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */
class TvShowDetailFragment : Fragment() {

    private lateinit var viewBinding: MoviedetailFragmentBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = MoviedetailFragmentBinding
            .inflate(inflater, container, false)
            .apply {
                item = args.movieItem
                lifecycleOwner = this@TvShowDetailFragment
            }

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as ToolbarListener).updateTitleToolbar(args.movieItem?.title.default)
    }
}