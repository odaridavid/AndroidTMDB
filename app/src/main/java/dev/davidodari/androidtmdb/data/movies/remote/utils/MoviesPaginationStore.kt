package dev.davidodari.androidtmdb.data.movies.remote.utils

import dev.davidodari.androidtmdb.core.model.Movie

object MoviesPaginationStore {

    private val movies = mutableSetOf<Movie>()

    fun addMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
    }

    fun clearMovies() {
        movies.clear()
    }

    fun getMovies(): List<Movie> = movies.toList()

}
