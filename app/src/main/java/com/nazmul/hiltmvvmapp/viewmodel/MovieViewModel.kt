package com.nazmul.hiltmvvmapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nazmul.hiltmvvmapp.common.Resource
import com.nazmul.hiltmvvmapp.data.PopularMovie
import com.nazmul.hiltmvvmapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel(){

    private val _popularMovie = MutableStateFlow<Resource<PopularMovie>>(Resource.Loading)
    val popularMovie get() = _popularMovie.asStateFlow()


    init {
        getPopularMovie()
    }


    private fun getPopularMovie() {
        viewModelScope.launch {
            movieRepository.getPupularMovie().collectLatest {
                _popularMovie.emit(it)
            }
        }
    }


}