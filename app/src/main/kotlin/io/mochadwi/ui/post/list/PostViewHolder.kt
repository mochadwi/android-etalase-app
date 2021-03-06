package io.mochadwi.ui.post.list

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.mochadwi.databinding.PostItemBinding
import io.mochadwi.ui.post.PostFragmentDirections
import io.mochadwi.util.base.BaseBindableAdapter

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */

class PostViewHolder(val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root),
        BaseBindableAdapter<PostItem> {

    override fun bind(data: PostItem) {
        binding.apply {
            item = data
            root.setOnClickListener {
                val toPostDetail = PostFragmentDirections.toPostDetailAction(
                        data
                )

                it.findNavController().navigate(toPostDetail)
            }
            executePendingBindings()
        }
    }
}