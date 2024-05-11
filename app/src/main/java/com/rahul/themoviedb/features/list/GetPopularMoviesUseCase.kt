package com.rahul.themoviedb.features.list

import com.rahul.themoviedb.core.ui.UIState
import com.rahul.themoviedb.core.usecase.UseCase
import com.rahul.themoviedb.features.list.repo.MovieRepository

class GetPopularMoviesUseCase(private val movieRepository: MovieRepository) :
    UseCase<GetPopularMoviesUseCase.Params> {
    data class Params(val language: String, val page: Int)

    override suspend fun execute(params: Params): UIState {
        return try {
            val popularMovies = movieRepository.getPopularMovies(params.language, params.page)
            UIState.SuccessState(popularMovies)
        } catch (e: Exception) {
            UIState.ErrorState(e.message ?: "An unknown error occurred")
        }
    }
}