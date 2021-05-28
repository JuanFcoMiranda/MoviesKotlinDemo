package com.jfma75.movieskotlindemo.data

import com.jfma75.movieskotlindemo.models.Movie

interface IMovieRepository {
    fun getPopularMovies(): List<Movie>
}