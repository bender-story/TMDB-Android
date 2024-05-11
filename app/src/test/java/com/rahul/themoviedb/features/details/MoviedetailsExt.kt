package com.rahul.themoviedb.features.details

import com.rahul.themoviedb.data.Genre
import com.rahul.themoviedb.data.MovieDetailsData
import com.rahul.themoviedb.data.ProductionCompany
import com.rahul.themoviedb.data.ProductionCountry
import com.rahul.themoviedb.data.SpokenLanguage

 fun getMovieDetailsStub() = MovieDetailsData(
    adult = false,
    backdropPath = "/path_to_backdrop",
    belongsToCollection = null,
    budget = 100000000,
    genres = listOf(Genre(id = 1, name = "Action")),
    homepage = "https://www.example.com",
    id = 1,
    imdbId = "tt1234567",
    originCountry = listOf("US"),
    originalLanguage = "en",
    originalTitle = "Original Movie Title",
    overview = "This is a movie overview.",
    popularity = 100.0,
    posterPath = "/path_to_poster",
    productionCompanies = listOf(
        ProductionCompany(id = 1, logoPath = "/path_to_logo", name = "Production Company", originCountry = "US")
    ),
    productionCountries = listOf(ProductionCountry(iso31661 = "US", name = "United States")),
    releaseDate = "2022-01-01",
    revenue = 200000000,
    runtime = 120,
    spokenLanguages = listOf(
        SpokenLanguage(englishName = "English", iso6391 = "en", name = "English")
    ),
    status = "Released",
    tagline = "This is a tagline.",
    title = "Movie Title",
    video = false,
    voteAverage = 8.0,
    voteCount = 1000
)