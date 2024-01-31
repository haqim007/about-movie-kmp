package dev.haqim.aboutmovie.domain.repository

import dev.haqim.aboutmovie.domain.model.Movie

interface IMovieRepository {
    suspend fun getMovies(page: Int): List<Movie>
    suspend fun getMovie(id: Int): Movie
}