package com.nazmul.hiltmvvmapp.pagin
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nazmul.hiltmvvmapp.data.Result
import com.nazmul.hiltmvvmapp.service.ApiService
import okio.IOException
import retrofit2.HttpException

class MoviePagingSource(private val apiService: ApiService) : PagingSource<Int,Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getNowPlayingMovies(page)
            val movies = response.body()?.results ?: emptyList()
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
        catch (e: IOException) {
            LoadResult.Error(throwable = e)
        } catch (e: HttpException) {
            LoadResult.Error(throwable = e)
        }
    }

}