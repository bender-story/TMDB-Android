package com.rahul.themoviedb.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.themoviedb.core.ui.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesListViewModel(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.LoadingState)
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        getPopularMovies("en", 1)
    }

    fun getPopularMovies(language: String, page: Int) {
        _uiState.value = UIState.LoadingState
        viewModelScope.launch {
            _uiState.value = getPopularMoviesUseCase.execute(GetPopularMoviesUseCase.Params(language, page))
        }
    }
}