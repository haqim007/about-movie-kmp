package dev.haqim.aboutmovie.di

import dev.haqim.aboutmovie.data.remote.MovieService
import dev.haqim.aboutmovie.data.remote.RemoteDataSource
import dev.haqim.aboutmovie.data.repository.MovieRepository
import dev.haqim.aboutmovie.domain.repository.IMovieRepository
import dev.haqim.aboutmovie.domain.usecase.GetMovieUseCase
import dev.haqim.aboutmovie.domain.usecase.GetMoviesUseCase
import dev.haqim.aboutmovie.util.provideDispatcher
import org.koin.dsl.module

private val dataModule = module { 
    factory { 
        RemoteDataSource(get(), get())
    }
    factory { MovieService() }
}

private val utilityModule = module { 
    factory { provideDispatcher() }
}

private val domainModule = module { 
    single<IMovieRepository> { MovieRepository(get()) }
    factory { GetMovieUseCase() }
    factory { GetMoviesUseCase() }
} 

private val sharedModules = listOf(domainModule, dataModule, utilityModule)

fun getSharedModules() = sharedModules