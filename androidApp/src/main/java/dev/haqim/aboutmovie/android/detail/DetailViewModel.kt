package dev.haqim.aboutmovie.android.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.haqim.aboutmovie.domain.model.Movie
import dev.haqim.aboutmovie.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getMovieUseCase: GetMovieUseCase,
    movieId: Int
): ViewModel() {
    private val _uiState = MutableStateFlow(DetailScreenState())
    val uiState: StateFlow<DetailScreenState>
        get() = _uiState
    
    init {
        loadMovie(movieId)
    }

    private fun loadMovie(movieId: Int) {
        viewModelScope.launch { 
            _uiState.update { state ->
                state.copy(
                    loading = true,
                    errorMessage = null
                )
            }
            
            try {
                _uiState.update { state ->
                    state.copy(
                        movie = getMovieUseCase(movieId),
                        loading = false
                    )
                }
            }catch (e: Throwable){
                _uiState.update { state ->
                    state.copy(
                        loading = false,
                        errorMessage = "Could not load detail: ${e.localizedMessage}"
                    )
                }
                Log.e(this@DetailViewModel::class.java.name, e.stackTraceToString())
            }
        }
    }

}

data class DetailScreenState(
    val loading: Boolean = false,
    val movie: Movie? = null,
    val errorMessage: String? = null
    
)