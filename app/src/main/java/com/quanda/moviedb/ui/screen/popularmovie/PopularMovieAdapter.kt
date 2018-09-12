package com.quanda.moviedb.ui.screen.popularmovie

import androidx.recyclerview.widget.DiffUtil
import com.quanda.moviedb.R
import com.quanda.moviedb.data.model.Movie
import com.quanda.moviedb.databinding.ItemMovieBinding
import com.quanda.moviedb.ui.base.BaseRecyclerAdapter2

class PopularMovieAdapter(
        val itemClickListener: ((Movie) -> Unit)? = null
) : BaseRecyclerAdapter2<Movie, ItemMovieBinding>(object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}) {

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_movie
    }

    override fun bindFirstTime(binding: ItemMovieBinding) {
        binding.apply {
            root.setOnClickListener {
                item?.apply {
                    itemClickListener?.invoke(this)
                }
            }
        }
    }

    override fun bindView(binding: ItemMovieBinding, item: Movie) {
        binding.apply {
            this.item = item
        }
    }
}