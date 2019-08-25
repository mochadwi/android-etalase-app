package io.mochadwi.ui.movie.list

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.mochadwi.databinding.MovieItemBinding
import io.mochadwi.ui.movie.MovieFragmentDirections
import io.mochadwi.util.base.BaseBindableAdapter

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */

class MovieViewHolder(val binding: MovieItemBinding) :
    RecyclerView.ViewHolder(binding.root),
    BaseBindableAdapter<MovieItem> {

    override fun bind(data: MovieItem) {
        binding.apply {
            item = data
            root.setOnClickListener {
                val toMovieDetail = MovieFragmentDirections.toMovieDetailAction(
                        data
                )

                it.findNavController().navigate(toMovieDetail)
            }
            executePendingBindings()
        }
    }
}