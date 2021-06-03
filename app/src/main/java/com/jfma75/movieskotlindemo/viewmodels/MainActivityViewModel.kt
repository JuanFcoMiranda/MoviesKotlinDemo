package com.jfma75.movieskotlindemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jfma75.movieskotlindemo.data.IMovieRepository
import com.jfma75.movieskotlindemo.data.MovieSource
import com.jfma75.movieskotlindemo.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val movieRepository: IMovieRepository) : ViewModel() {
    fun getListData(): Flow<PagingData<Movie>> {
        val pagingSourceFactory =  { MovieSource(movieRepository) }
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = 4,
                initialLoadSize = 8
            ),
            //remoteMediator = MoviesRemoteMediator(service = photosApi, database = database),
            pagingSourceFactory = pagingSourceFactory
        ).flow.cachedIn(viewModelScope)
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 8
    }
}