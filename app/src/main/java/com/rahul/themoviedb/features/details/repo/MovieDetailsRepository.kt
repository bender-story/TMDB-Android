package com.rahul.themoviedb.features.details.repo

import com.rahul.themoviedb.data.MovieDetailsData

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int, language: String): MovieDetailsData
}