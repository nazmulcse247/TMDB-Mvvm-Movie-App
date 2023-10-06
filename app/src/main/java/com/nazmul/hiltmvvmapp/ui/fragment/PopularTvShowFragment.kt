package com.nazmul.hiltmvvmapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nazmul.hiltmvvmapp.common.Resource
import com.nazmul.hiltmvvmapp.databinding.FragmentPopularTvShowBinding
import com.nazmul.hiltmvvmapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class PopularTvShowFragment : Fragment() {

    private lateinit var binding: FragmentPopularTvShowBinding
    private val viewModel : MovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPopularTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPopularUIObserver()
    }

    private fun getPopularUIObserver(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.popularMovie.collectLatest {
                when(it){
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), ""+it.data.results.size, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Error -> {
                        Timber.tag("tag").d("getPopularUIObserver%s", it.throwable.localizedMessage)
                    }
                }
            }
        }
    }

}