package com.jfma75.movieskotlindemo.data

import com.jfma75.movieskotlindemo.models.Movie
import com.jfma75.movieskotlindemo.movies

class MovieRepository: IMovieRepository {
    override fun getPopularMovies(): List<Movie> {
        //return movies.drop(page * items).take(items)
        return movies
    }
}