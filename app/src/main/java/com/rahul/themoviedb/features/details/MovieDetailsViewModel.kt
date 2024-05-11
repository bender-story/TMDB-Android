package com.rahul.themoviedb.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.themoviedb.core.ui.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val getMovieDetailsUseCase: GetMovieDetailsUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<UIState>(UIState.LoadingState)
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    fun getMovieDetails(movieId: Int, language: String) {
        _uiState.value = UIState.LoadingState
        viewModelScope.launch {
            _uiState.value =  getMovieDetailsUseCase.execute(GetMovieDetailsUseCase.Params(movieId, language))
        }
    }
}