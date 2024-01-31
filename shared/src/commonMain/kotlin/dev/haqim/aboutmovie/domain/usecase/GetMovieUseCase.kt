package dev.haqim.aboutmovie.domain.usecase

import dev.haqim.aboutmovie.domain.model.Movie
import dev.haqim.aboutmovie.domain.repository.IMovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMovieUseCase: KoinComponent {
    private val repository: IMovieRepository by inject()
    
    @Throws(Exception::class)
    suspend operator fun invoke(id: Int): Movie{
        return repository.getMovie(id)
    }
}