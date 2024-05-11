package com.rahul.themoviedb.core.ui

sealed class UIState {
    data object LoadingState : UIState()
    data class SuccessState<T>(val data: T) : UIState()
    data class ErrorState(val errorMessage: String) : UIState()
}