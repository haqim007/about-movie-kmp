package dev.haqim.aboutmovie.data.remote

import io.ktor.client.call.*
import io.ktor.client.request.*

internal class MovieService: KtorApi() {
    suspend fun getMovies(page: Int = 1): MoviesResponse = client.get { 
        pathUrl("movie/popular")
        parameter("page", page)
    }.body()
    
    suspend fun getMovie(movieId: Int): MovieResponse = client.get { 
        pathUrl("movie/${movieId}")
    }.body()
}