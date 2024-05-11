package com.rahul.themoviedb.features.details.repo

import com.rahul.themoviedb.core.network.ServiceInterface
import com.rahul.themoviedb.data.MovieDetailsData
class MovieDetailsRepositoryImpl(private val serviceInterface: ServiceInterface) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int, language: String): MovieDetailsData {
        return serviceInterface.getMovieDetails(movieId, language)
    }
}