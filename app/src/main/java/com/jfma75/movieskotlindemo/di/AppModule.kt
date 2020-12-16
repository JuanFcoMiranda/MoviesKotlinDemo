package com.jfma75.movieskotlindemo.di

import android.content.Context
import com.jfma75.movieskotlindemo.MoviesKotlinDemoApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesApplication(@ApplicationContext app: Context) : MoviesKotlinDemoApp = app as MoviesKotlinDemoApp

    @Singleton
    @Provides
    fun providesRandomString() : String = "asjdflkasjflkjslkfjslkfjlkasdf"
}