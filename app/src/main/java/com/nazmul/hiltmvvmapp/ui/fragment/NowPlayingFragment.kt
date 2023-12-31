package com.nazmul.hiltmvvmapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.nazmul.hiltmvvmapp.R
import com.nazmul.hiltmvvmapp.common.adapter.LoadStateAdapter
import com.nazmul.hiltmvvmapp.common.adapter.NowPlayingAdapter
import com.nazmul.hiltmvvmapp.common.setUpGridRecyclerView
import com.nazmul.hiltmvvmapp.databinding.FragmentNowPlayingBinding
import com.nazmul.hiltmvvmapp.ui.base.BaseFragment
import com.nazmul.hiltmvvmapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class NowPlayingFragment : BaseFragment<FragmentNowPlayingBinding>() {
    private val viewModel : MovieViewModel by viewModels()
    override fun viewBindingLayout(): FragmentNowPlayingBinding = FragmentNowPlayingBinding.inflate(layoutInflater)

    private val adapterNowPlayingMovies: NowPlayingAdapter by lazy { NowPlayingAdapter(::onClickMovieItem) }

    override fun initializeView(savedInstanceState: Bundle?) {
        getNowPlayingUIObserver()
    }

    private fun onClickMovieItem(movie: Int) {
       showToastMessage("Clicked$movie")
    }

    private fun getNowPlayingUIObserver() {
        with(viewModel){
            with(binding){
                viewLifecycleOwner.lifecycleScope.launch {
                    nowPlayingMovie.collectLatest {

                        // Creating Contact Adapter For Paging Footer Span Count
                        val nowPlayingAdapter = adapterNowPlayingMovies.withLoadStateFooter(
                            footer = LoadStateAdapter {
                                adapterNowPlayingMovies.retry()
                            }
                        )

                        rvNowPlayingMovie.layoutManager =
                            GridLayoutManager(context, 2).apply {
                                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                                    override fun getSpanSize(position: Int): Int {
                                        return if (nowPlayingAdapter.getItemViewType(position) in
                                            arrayOf(1)
                                        ) spanCount else 1
                                    }

                                }
                            }

                        rvNowPlayingMovie.adapter = nowPlayingAdapter
                        adapterNowPlayingMovies.submitData(lifecycle,it)

                        adapterNowPlayingMovies.loadStateFlow.collectLatest { loadStates ->
                            when (loadStates.refresh) {
                                is LoadState.Loading -> {
                                    discoverLoading.visibility = View.VISIBLE
                                    discoverLoading.startShimmer()
                                    discoverLoading.visibility = View.GONE
                                }
                                is LoadState.NotLoading -> {
                                    discoverLoading.visibility = View.GONE
                                    discoverLoading.stopShimmer()
                                    rvNowPlayingMovie.visibility = View.VISIBLE
                                }
                                is LoadState.Error -> {
                                    Timber.d("Unknown Error")
                                }

                            }
                        }

                    }
                }
            }
        }
    }


}