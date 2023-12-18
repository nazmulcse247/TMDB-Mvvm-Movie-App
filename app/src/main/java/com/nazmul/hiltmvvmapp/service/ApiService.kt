package com.nazmul.hiltmvvmapp.service

import com.nazmul.hiltmvvmapp.data.PopularMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<PopularMovie>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): Response<PopularMovie>


}