package io.mochadwi.ui.movie

import android.content.ContentUris
import android.net.Uri
import android.os.Bundle
import android.provider.BaseColumns
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import io.mochadwi.R
import io.mochadwi.data.datasource.local.provider.EtalaseContentProvider.Companion.URI_FAVOURITE
import io.mochadwi.data.datasource.local.provider.FavouriteProvider.Companion.toContentValues
import io.mochadwi.databinding.MoviedetailFragmentBinding
import io.mochadwi.domain.*
import io.mochadwi.ui.movie.list.MovieItem
import io.mochadwi.ui.movie.mapper.MovieModelMapper
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
    private var movieId: Int = 0
    private lateinit var pathUriWithId: Uri
    private val whereQuery = "${BaseColumns._ID} = ?"
    private lateinit var arrayOfMovieId: Array<String>

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
                vm.run {
                    if (isMovieFavourite.get().default) {
                        deleteFromContentProvider(pathUriWithId, whereQuery, arrayOfMovieId)
                    } else {
                        val movieItem = MovieModelMapper.from(args.movieItem
                                ?: MovieItem(id = -1, isFavourite = false))
                        addToContentProvider(URI_FAVOURITE, toContentValues(movieItem.copy(isFavourite = true)))
                    }
                }
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
        setupData()

        vm.getContentProviderById(pathUriWithId, whereQuery, arrayOfMovieId)

        return viewBinding.root
    }

    private fun setupData() {
        movieId = args.movieItem?.id ?: -1
        pathUriWithId = try {
            ContentUris.withAppendedId(URI_FAVOURITE, movieId.toLong())
        } catch (e: Exception) {
            Uri.EMPTY
        }
        arrayOfMovieId = arrayOf("$movieId")
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
                    is FavouriteListState -> {
                        isMovieFavourite.set(state.single.isFavourite.default)
                    }
                    is FavouriteState -> showSuccess(state.isFavourite)
                    is ErrorState -> showError(state.error)
                }
            })
        }
    }

    private fun showSuccess(isFavourite: Boolean) {
        toastSpammable(getString(
                if (isFavourite) R.string.favourite
                else R.string.not_favourite
        ))
    }

    private fun showError(err: Throwable) {
        toastSpammable(err.localizedMessage)
    }
}