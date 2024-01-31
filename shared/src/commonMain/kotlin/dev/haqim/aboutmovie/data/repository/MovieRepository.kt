package dev.haqim.aboutmovie.data.repository

import dev.haqim.aboutmovie.data.remote.RemoteDataSource
import dev.haqim.aboutmovie.data.util.toMovie
import dev.haqim.aboutmovie.domain.model.Movie
import dev.haqim.aboutmovie.domain.repository.IMovieRepository

internal class MovieRepository(
    private val remoteDataSource: RemoteDataSource
): IMovieRepository {
    override suspend fun getMovies(page: Int): List<Movie> {
        return remoteDataSource.getMovies(page).results.map { it.toMovie() }
    }

    override suspend fun getMovie(id: Int): Movie {
        return remoteDataSource.getMovie(id).toMovie()
    }
}