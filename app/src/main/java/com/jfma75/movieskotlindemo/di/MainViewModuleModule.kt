package com.jfma75.movieskotlindemo.di

import com.jfma75.movieskotlindemo.data.IMovieRepository
import com.jfma75.movieskotlindemo.data.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object MainViewModuleModule {
    @Provides
    fun providesMovieRepository() : IMovieRepository = MovieRepository()
}