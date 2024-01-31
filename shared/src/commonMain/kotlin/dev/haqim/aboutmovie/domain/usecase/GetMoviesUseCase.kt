package dev.haqim.aboutmovie.domain.usecase

import dev.haqim.aboutmovie.domain.model.Movie
import dev.haqim.aboutmovie.domain.repository.IMovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMoviesUseCase: KoinComponent {
    private val repository: IMovieRepository by inject()
    
    @Throws(Exception::class)
    suspend operator fun invoke(page: Int): List<Movie>{
        return repository.getMovies(page)
    }
}