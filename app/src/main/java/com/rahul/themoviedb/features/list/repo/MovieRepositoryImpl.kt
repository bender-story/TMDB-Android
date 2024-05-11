package com.rahul.themoviedb.features.list.repo

import com.rahul.themoviedb.core.network.ServiceInterface
import com.rahul.themoviedb.data.PopularMovies

class MovieRepositoryImpl(private val serviceInterface: ServiceInterface) : MovieRepository {
    override suspend fun getPopularMovies(language: String, page: Int): PopularMovies {
        return serviceInterface.getPopularMovies(language, page)
    }
}