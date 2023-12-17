package com.nazmul.hiltmvvmapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nazmul.hiltmvvmapp.common.Resource
import com.nazmul.hiltmvvmapp.common.adapter.PopularTvShowAdapter
import com.nazmul.hiltmvvmapp.common.setUpGridRecyclerView
import com.nazmul.hiltmvvmapp.databinding.FragmentPopularTvShowBinding
import com.nazmul.hiltmvvmapp.ui.base.BaseFragment
import com.nazmul.hiltmvvmapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class PopularTvShowFragment : BaseFragment<FragmentPopularTvShowBinding>() {

    private val viewModel : MovieViewModel by viewModels()

    override fun viewBindingLayout(): FragmentPopularTvShowBinding = FragmentPopularTvShowBinding.inflate(layoutInflater)

    private val popularTvShowAdapter = PopularTvShowAdapter{
        Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun initializeView(savedInstanceState: Bundle?) {
        requireActivity().setUpGridRecyclerView(binding.rvPopularTvShow,popularTvShowAdapter,2)
        getPopularUIObserver()
    }

    private fun getPopularUIObserver(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.popularMovie.collectLatest {
                when(it){
                    is Resource.Loading -> {
                        binding.discoverLoading.startShimmer()
                    }
                    is Resource.Success -> {
                        binding.discoverLoading.stopShimmer()
                        binding.discoverLoading.visibility = View.GONE
                        binding.rvPopularTvShow.visibility = View.VISIBLE
                        popularTvShowAdapter.submitList(it.data.results)

                    }
                    is Resource.Error -> {

                    }
                }
            }
        }
    }

}