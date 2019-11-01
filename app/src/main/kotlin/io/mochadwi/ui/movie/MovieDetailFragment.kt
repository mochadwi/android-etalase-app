package io.mochadwi.ui.movie

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import io.mochadwi.R
import io.mochadwi.databinding.MoviedetailFragmentBinding
import io.mochadwi.domain.ErrorState
import io.mochadwi.domain.FavouriteState
import io.mochadwi.ui.favourite.mapper.FavouriteItemMapper
import io.mochadwi.ui.movie.list.MovieItem
import io.mochadwi.util.base.ToolbarListener
import io.mochadwi.util.ext.default
import io.mochadwi.util.ext.toastSpammable
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */
class MovieDetailFragment : Fragment() {

    private lateinit var viewBinding: MoviedetailFragmentBinding
    private val vm: MovieViewModel by viewModel()
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.favourite_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actFavourite -> {
                vm.addToFavourite(FavouriteItemMapper.from(
                        args.movieItem ?: MovieItem(id = -1)
                ))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        viewBinding = MoviedetailFragmentBinding
                .inflate(inflater, container, false)
                .apply {
                    item = args.movieItem
                    lifecycleOwner = this@MovieDetailFragment
                }

        setupObserver()

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as ToolbarListener).updateTitleToolbar(args.movieItem?.title.default)
    }

    private fun setupObserver() {
        vm.run {
            // Observe ComposeState
            states.observe(viewLifecycleOwner, Observer { state ->
                when (state) {
                    is FavouriteState -> showSuccess(state)
                    is ErrorState -> showError(state.error)
                }
            })
        }
    }

    private fun showSuccess(state: FavouriteState) {
        toastSpammable(
                getString(if (state.isFavourite) R.string.favourite else R.string.error_unknown)
        )
    }

    private fun showError(err: Throwable) {
        toastSpammable(err.localizedMessage)
    }
}