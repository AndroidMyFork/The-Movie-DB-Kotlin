package com.quanda.moviedb.ui.screen.popularmovie

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.quanda.moviedb.BR
import com.quanda.moviedb.data.constants.MovieListType
import com.quanda.moviedb.data.model.Movie
import com.quanda.moviedb.databinding.FragmentLoadmoreRefreshBinding
import com.quanda.moviedb.ui.base.BaseLoadMoreRefreshFragment
import com.quanda.moviedb.ui.screen.moviedetail.MovieDetailFragment

class PopularMovieFragment : BaseLoadMoreRefreshFragment<FragmentLoadmoreRefreshBinding, PopularMovieViewModel, Movie>() {

    companion object {
        const val TAG = "PopularMovieFragment"
        const val TYPE = "TYPE"

        fun newInstance(type: Int) = PopularMovieFragment().apply {
            arguments = Bundle().apply {
                putInt(TYPE, type)
            }
        }
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel: PopularMovieViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(
                PopularMovieViewModel::class.java)
                .apply {
                    mode.value = arguments?.getInt(TYPE) ?: MovieListType.POPULAR.type
                }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = PopularMovieAdapter(itemClickListener = { goToMovieDetail(it) })
        viewBinding.apply {
            root.setBackgroundColor(Color.BLACK)
            recyclerView.apply {
                layoutManager = GridLayoutManager(context, 2)
                this.adapter = adapter
            }
        }
        viewModel.apply {
            listItem.observe(this@PopularMovieFragment, Observer {
                adapter.submitList(it)
            })
            firstLoad()
        }
    }

    fun goToMovieDetail(movie: Movie) {
        replaceChildFragment(containerViewId = viewBinding.parent.id,
                fragment = MovieDetailFragment.newInstance(movie),
                TAG = MovieDetailFragment.TAG,
                addToBackStack = true)
    }
}