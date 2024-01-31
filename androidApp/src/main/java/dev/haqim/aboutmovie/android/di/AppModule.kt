package dev.haqim.aboutmovie.android.di

import dev.haqim.aboutmovie.android.detail.DetailViewModel
import dev.haqim.aboutmovie.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module { 
    viewModel{
        HomeViewModel(get())
    }
    viewModel { params ->
        DetailViewModel(
            getMovieUseCase = get(), movieId = params.get()
        )
    }
}