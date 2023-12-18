package com.nazmul.hiltmvvmapp.repository

import androidx.paging.PagingData
import com.nazmul.hiltmvvmapp.common.Resource
import com.nazmul.hiltmvvmapp.data.PopularMovie
import com.nazmul.hiltmvvmapp.data.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPupularMovie(): Flow<Resource<PopularMovie>>
    fun getNowPlayingMovie() : Flow<PagingData<Result>>


}