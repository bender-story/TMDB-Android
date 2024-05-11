package com.rahul.themoviedb.features.details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rahul.themoviedb.Constants
import com.rahul.themoviedb.R
import com.rahul.themoviedb.common.ui.CustomRowView
import com.rahul.themoviedb.common.ui.GradientAsyncImage
import com.rahul.themoviedb.common.ui.RatingBar
import com.rahul.themoviedb.common.utils.convertMinutesToHoursAndMinutes
import com.rahul.themoviedb.data.Genre
import com.rahul.themoviedb.data.MovieDetailsData
import com.rahul.themoviedb.data.ProductionCompany
import com.rahul.themoviedb.data.ProductionCountry
import com.rahul.themoviedb.data.SpokenLanguage

@Composable
fun MovieDetailsView(movieDetails: MovieDetailsData) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        val imagePath = Constants.IMAGE_BASE_URL + movieDetails.backdropPath
        GradientAsyncImage(imagePath, movieDetails.title)
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Constants.IMAGE_BASE_URL + movieDetails.posterPath)
                        .crossfade(true)
                        .build(),
                    contentDescription = movieDetails.title,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(50.dp)
                        .border(2.dp, Color.Black)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = movieDetails.title, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(8.dp))
            RatingBar(rating = movieDetails.voteAverage, votes = movieDetails.voteCount)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = stringResource(id = R.string.movie_details_story_line), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = movieDetails.overview, fontStyle = FontStyle.Italic, color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            CustomRowView(stringResource(id = R.string.movie_details_runtime),movieDetails.runtime.convertMinutesToHoursAndMinutes())
            Spacer(modifier = Modifier.height(8.dp))
            CustomRowView(stringResource(id = R.string.movie_details_release_date),movieDetails.releaseDate)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MovieDetailsViewPreview() {
    val movieDetails = MovieDetailsData(
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
            ProductionCompany(
                id = 1,
                logoPath = "/path_to_logo",
                name = "Production Company",
                originCountry = "US"
            )
        ),
        productionCountries = listOf(ProductionCountry(iso31661 = "US", name = "United States")),
        releaseDate = "2022-01-01",
        revenue = 200000000,
        runtime = 120,
        spokenLanguages = listOf(
            SpokenLanguage(
                englishName = "English",
                iso6391 = "en",
                name = "English"
            )
        ),
        status = "Released",
        tagline = "This is a tagline.",
        title = "Movie Title",
        video = false,
        voteAverage = 8.0,
        voteCount = 1000
    )
    MovieDetailsView(movieDetails)
}