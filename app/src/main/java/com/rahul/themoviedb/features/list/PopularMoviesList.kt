package com.rahul.themoviedb.features.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rahul.themoviedb.Constants
import com.rahul.themoviedb.common.navigation.NavDestinations
import com.rahul.themoviedb.common.ui.GradientAsyncImage
import com.rahul.themoviedb.common.ui.RatingBar
import com.rahul.themoviedb.data.Movie


@Composable
fun PopularMoviesList(movies: List<Movie>, listState: LazyListState, navController: NavController) {
    LazyColumn(state = listState) {
        items(movies) { movie ->
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("${NavDestinations.MOVIE_DETAILS_ROUTE}/${movie.id.toString()}")
                    },
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                val imagePath = Constants.IMAGE_BASE_URL + movie.posterPath
                print("imagePath: $imagePath")
                Column(modifier = Modifier.padding(8.dp)) {
                    GradientAsyncImage(imagePath = imagePath, title = movie.title)
//                    AsyncImage(
//                        model = ImageRequest.Builder(LocalContext.current)
//                            .data(imagePath)
//                            .crossfade(true)
//                            .build(),
//                        contentDescription = movie.title,
//                        contentScale = ContentScale.FillBounds,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .border(2.dp, Color.Black)
//                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(
                            text = movie.title,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        RatingBar(rating = (movie.voteAverage / 2).toInt(), votes = movie.voteCount)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopularMoviesListPreview() {
    val movies = listOf(
        Movie(
            adult = false,
            backdropPath = "/path_to_backdrop",
            id = 1,
            originalLanguage = "en",
            originalTitle = "Original Movie Title",
            overview = "This is a movie overview.",
            popularity = 100.0,
            posterPath = "/path_to_poster",
            releaseDate = "2022-01-01",
            title = "Movie Title",
            video = false,
            voteAverage = 8.0,
            voteCount = 1000,
            genreIds = listOf(1, 2, 3)
        )
    )
    PopularMoviesList(movies,  rememberLazyListState(), rememberNavController())
}
