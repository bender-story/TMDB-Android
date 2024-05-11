package com.rahul.themoviedb.features.details


import com.rahul.themoviedb.core.ui.UIState
import com.rahul.themoviedb.core.usecase.UseCase
import com.rahul.themoviedb.features.details.repo.MovieDetailsRepository

class GetMovieDetailsUseCase(private val movieDetailsRepository: MovieDetailsRepository) :
    UseCase<GetMovieDetailsUseCase.Params> {
    data class Params(val movieId: Int, val language: String)

    override suspend fun execute(params: Params): UIState {
        return try {
            val movieDetails = movieDetailsRepository.getMovieDetails(params.movieId, params.language)
            UIState.SuccessState(movieDetails)
        } catch (e: Exception) {
            UIState.ErrorState(e.message ?: "An unknown error occurred")
        }
    }
}