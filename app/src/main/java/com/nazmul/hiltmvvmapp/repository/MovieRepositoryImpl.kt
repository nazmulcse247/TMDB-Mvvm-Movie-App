package com.nazmul.hiltmvvmapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nazmul.hiltmvvmapp.common.AppConstraits.NETWORK_PAGE_SIZE
import com.nazmul.hiltmvvmapp.common.Resource
import com.nazmul.hiltmvvmapp.data.PopularMovie
import com.nazmul.hiltmvvmapp.data.Result
import com.nazmul.hiltmvvmapp.pagin.MoviePagingSource
import com.nazmul.hiltmvvmapp.service.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) : MovieRepository {

    override fun getPupularMovie(): Flow<Resource<PopularMovie>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.getPopularMovies()
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()!!))
            }
        }
        catch (t : Throwable) {
            emit(Resource.Error(t))
        }

    }

    override fun getNowPlayingMovie(): Flow<PagingData<Result>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            prefetchDistance = 2,
            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
            jumpThreshold = Int.MIN_VALUE,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {
            MoviePagingSource(
                apiService
            )
        }
    ).flow


}