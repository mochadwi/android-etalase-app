package io.mochadwi.ui.movie

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import io.mochadwi.R
import io.mochadwi.databinding.MovieFragmentBinding
import io.mochadwi.domain.ErrorState
import io.mochadwi.domain.FavouriteListState
import io.mochadwi.domain.LoadingState
import io.mochadwi.domain.MovieListState
import io.mochadwi.ui.movie.list.MovieItem
import io.mochadwi.ui.movie.mapper.MovieModelMapper
import io.mochadwi.util.base.BaseApiModel
import io.mochadwi.util.base.BaseUserActionListener
import io.mochadwi.util.base.ToolbarListener
import io.mochadwi.util.ext.coroutineLaunch
import io.mochadwi.util.ext.fromJson
import io.mochadwi.util.ext.putArgs
import io.mochadwi.util.list.EndlessRecyclerOnScrollListener
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.channels.consumeEach
import kotlinx.serialization.serializer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import retrofit2.HttpException

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */
class MovieFragment : Fragment(), BaseUserActionListener {

    private lateinit var viewBinding: MovieFragmentBinding
    private val viewModel by sharedViewModel<MovieViewModel>()

    private var isFavourite = false
    private lateinit var onLoadMore: EndlessRecyclerOnScrollListener

    companion object {
        private const val IS_FAVOURITE = "is_favourite"
        fun newInstance(isFavourite: Boolean) = MovieFragment().putArgs {
            putBoolean(IS_FAVOURITE, isFavourite)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        isFavourite = arguments?.getBoolean(IS_FAVOURITE) == true

        return if (::viewBinding.isInitialized) viewBinding.root
        else {
            viewBinding = MovieFragmentBinding
                .inflate(inflater, container, false)
                .apply {
                    listener = this@MovieFragment
                    vm = viewModel
                    lifecycleOwner = this@MovieFragment
                }

            (requireActivity() as ToolbarListener).updateTitleToolbar(
                    newTitle = if (isFavourite) getString(R.string.favourite)
                    else getString(R.string.movie)
            )

            setupObserver()
            setupData()

            viewBinding.root
        }
    }

    override fun onResume() {
        super.onResume()
        if (::onLoadMore.isInitialized) onLoadMore.resetState()
    }

    override fun onRefresh() {
        Handler().postDelayed({ pullToRefresh() }, 1000)
    }

    private fun setupData() {
        viewModel.apply {
            if (::onLoadMore.isInitialized) onLoadMore.resetState()
            movieListSet.clear()
            if (isFavourite) getMovieFavourites() else getMovies()
        }
    }

    private fun setupObserver() = with(viewModel) {
        // Observe ComposeState
        states.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is LoadingState -> showIsLoading()

                is FavouriteListState -> {
                    showItemList(
                            movies = state.list.map { MovieModelMapper.from(it) })
                }

                is MovieListState -> {
                    showItemList(
                        movies = state.list.map { MovieModelMapper.from(it) })
                }

                is ErrorState -> showError(state.error)
            }
        })

        coroutineLaunch(Main) {
            keywords.consumeEach { searchMovies(it) }
        }
    }

    private fun pullToRefresh() {
        viewModel.apply {
            isRefreshing.set(true)
            setupData()
        }
    }

    private fun showIsLoading() = with(viewModel) {
        isError.set(false)
        progress.set(true)
        isRefreshing.set(false)
    }

    // TODO: Fix this bloated converter err handle
    private fun showError(err: Throwable) = with(viewBinding) {
        viewModel.apply {
            isError.set(true)
            progress.set(false)
            isRefreshing.set(false)

            val humanizedMsg = when (err) {
                is HttpException -> {
                    when (err.code()) {
                        403 -> "Try again later after a minute, you've exceeded the rate limit!"
                        422 -> "You have to type meaningful character, e.g: john doe"
                        else -> converter<Any>(err)?.message
                    }
                }

                else -> err.localizedMessage
            }

            errMsg.set(humanizedMsg)

            error.btnRetry.setOnClickListener { setupData() }
        }
    }

    private fun showItemList(movies: List<MovieItem>) {
        viewModel.apply {
            // TODO: @mochadwi clearing list doesn't good for pagination?
            movieListSet.addAll(movies.toMutableList())
            isRefreshing.set(false)
            progress.set(false)
            isError.set(false)
        }
    }

    // TODO: Move this into utils class
    private inline fun <reified T : Any> converter(error: HttpException): BaseApiModel<T>? {
        var baseDao: BaseApiModel<T>? = null
        try {
            val body = error.response()?.errorBody()
            baseDao = body?.string()?.fromJson(BaseApiModel.serializer(T::class.serializer()))
        } catch (exception: Exception) {
            // no-op
        }

        return baseDao
    }
}