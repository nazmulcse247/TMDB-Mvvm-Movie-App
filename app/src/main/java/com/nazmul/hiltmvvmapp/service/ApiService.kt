package com.nazmul.hiltmvvmapp.service

import com.nazmul.hiltmvvmapp.data.PopularMovie
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<PopularMovie>


}