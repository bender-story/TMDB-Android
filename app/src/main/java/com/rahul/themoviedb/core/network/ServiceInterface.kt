package com.rahul.themoviedb.core.network

import com.rahul.themoviedb.data.MovieDetails
import com.rahul.themoviedb.data.PopularMovies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceInterface {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): PopularMovies

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): MovieDetails
}