package com.nazmul.hiltmvvmapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nazmul.hiltmvvmapp.common.Resource
import com.nazmul.hiltmvvmapp.data.PopularMovie
import com.nazmul.hiltmvvmapp.data.Result
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

    private val _nowPlayingMovie = MutableStateFlow<PagingData<Result>>(PagingData.empty())
    val nowPlayingMovie get() = _nowPlayingMovie.asStateFlow()


    init {
        getPopularMovie()
        getNowPlayingMovie()
    }


    private fun getPopularMovie() {
        viewModelScope.launch {
            movieRepository.getPupularMovie().collectLatest {
                _popularMovie.emit(it)
            }
        }
    }

    private fun getNowPlayingMovie() {
        viewModelScope.launch {
            movieRepository.getNowPlayingMovie().cachedIn(viewModelScope).collectLatest {
                _nowPlayingMovie.emit(it)
            }
        }
    }


}