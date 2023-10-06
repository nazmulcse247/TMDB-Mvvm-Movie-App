package com.nazmul.hiltmvvmapp.repository

import com.nazmul.hiltmvvmapp.common.Resource
import com.nazmul.hiltmvvmapp.data.PopularMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPupularMovie(): Flow<Resource<PopularMovie>>


}