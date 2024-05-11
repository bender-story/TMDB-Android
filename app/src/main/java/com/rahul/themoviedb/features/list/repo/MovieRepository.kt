package com.rahul.themoviedb.features.list.repo

import com.rahul.themoviedb.data.PopularMovies

interface MovieRepository {
    suspend fun getPopularMovies(language: String, page: Int): PopularMovies
}