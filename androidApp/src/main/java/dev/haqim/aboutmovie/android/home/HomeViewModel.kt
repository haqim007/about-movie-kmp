package dev.haqim.aboutmovie.android.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.haqim.aboutmovie.domain.model.Movie
import dev.haqim.aboutmovie.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel (
    private val getMoviesUseCase: GetMoviesUseCase
): ViewModel(){
    
    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState: StateFlow<HomeScreenState>
        get() = _uiState
    private var currentPage = 1

    init {
        loadMovies(false)
    }
    
    fun loadMovies(isSwipeRefresh: Boolean = false){
        if (uiState.value.loading) return
        if (isSwipeRefresh) currentPage = 1
        if (currentPage == 1) _uiState.update { state -> state.copy(refreshing = true) }
        
        viewModelScope.launch {
            _uiState.update { state -> state.copy(loading  = true) }
            try {
                val resultMovies = getMoviesUseCase(page = currentPage)
                _uiState.update { state ->
                    state.copy(
                        movies = state.movies + resultMovies,
                        loading  = false,
                        refreshing = false,
                        loadFinished = resultMovies.isEmpty(),
                    )
                }
                this@HomeViewModel.currentPage += 1
                
            }catch (e: Throwable){
                _uiState.update { state -> 
                    state.copy(
                        loading  = false,
                        refreshing = false,
                        loadFinished = true,
                        errorMessage = "Could not load movies: ${e.message}"
                    ) 
                }
            }
            
        }
        
        
    }
    
}

data class HomeScreenState(
    val loading: Boolean = false,
    val refreshing: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val errorMessage: String? = null,
    val loadFinished: Boolean = false,
    
)