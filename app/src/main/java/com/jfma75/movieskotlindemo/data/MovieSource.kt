package com.jfma75.movieskotlindemo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jfma75.movieskotlindemo.models.Movie

class MovieSource (private val movieRepository: IMovieRepository) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val movieListResponse = movieRepository.getPopularMovies()

            LoadResult.Page(
                data = movieListResponse,
                prevKey = if (nextPage == FIRST_PAGE_INDEX) null else nextPage - 1,
                nextKey = if (movieListResponse.isEmpty()) null else nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}