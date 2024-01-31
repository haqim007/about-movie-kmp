package dev.haqim.aboutmovie.data.remote

import dev.haqim.aboutmovie.util.Dispatcher
import kotlinx.coroutines.withContext

internal class RemoteDataSource(
    private val apiService: MovieService,
    private val dispatcher: Dispatcher
) {
    suspend fun getMovies(page: Int) = withContext(dispatcher.io){
        apiService.getMovies(page)
    }
    
    suspend fun getMovie(id: Int) = withContext(dispatcher.io){
        apiService.getMovie(id)
    }
}