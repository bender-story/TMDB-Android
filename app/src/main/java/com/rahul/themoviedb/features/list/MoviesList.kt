package com.rahul.themoviedb.features.list

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rahul.themoviedb.Constants
import com.rahul.themoviedb.R
import com.rahul.themoviedb.common.ui.CircularLoader
import com.rahul.themoviedb.common.ui.RatingBar
import com.rahul.themoviedb.common.ui.ShowErrorDialog
import com.rahul.themoviedb.core.ui.UIState
import com.rahul.themoviedb.data.Movie
import com.rahul.themoviedb.data.PopularMovies
import com.rahul.themoviedb.ui.theme.OrangeDark
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesList() {
    val viewModel: MoviesListViewModel = getViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val showDialog = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf("") }
    val loadData = remember { mutableStateOf(true) }

    val listState = rememberLazyListState()
    LaunchedEffect(loadData) {
        if (loadData.value) {
            viewModel.getPopularMovies("en", 1)
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.movie_list_header), fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = OrangeDark,
                    titleContentColor = Color.White,
                ),
            )
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (uiState) {
                is UIState.LoadingState -> {
                    CircularLoader()
                }

                is UIState.SuccessState<*> -> {
                    val successState = uiState as UIState.SuccessState<PopularMovies>
                    PopularMoviesList(successState.data.results, listState)
                    loadData.value = false
                }

                is UIState.ErrorState -> {
                    errorMessage.value = (uiState as UIState.ErrorState).errorMessage
                    showDialog.value = true
                    loadData.value = false
                }
            }

            ShowErrorDialog(showDialog, errorMessage) {
                loadData.value = true
                showDialog.value = false
            }
        }
    }
}

@Composable
fun PopularMoviesList(movies: List<Movie>, listState: LazyListState) {
    LazyColumn(state = listState) {
        items(movies) { movie ->
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                val imagePath = Constants.IMAGE_BASE_URL + movie.posterPath
                print("imagePath: $imagePath")
                Column(modifier = Modifier.padding(8.dp)) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imagePath)
                            .crossfade(true)
                            .build(),
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f)
                            .border(2.dp, Color.Black)
                            .shadow(2.dp)// Add this line to add a border
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(
                            text = movie.title,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold
                        )
                        RatingBar(rating = (movie.voteAverage / 2).toInt())
                    }
                }
            }
        }
    }
}