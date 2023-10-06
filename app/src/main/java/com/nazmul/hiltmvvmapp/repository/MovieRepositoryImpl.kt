package com.nazmul.hiltmvvmapp.repository

import com.nazmul.hiltmvvmapp.common.Resource
import com.nazmul.hiltmvvmapp.data.PopularMovie
import com.nazmul.hiltmvvmapp.service.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) : MovieRepository {

    override fun getPupularMovie(): Flow<Resource<PopularMovie>> = flow{
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


}