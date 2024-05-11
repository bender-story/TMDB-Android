package com.rahul.themoviedb.features.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rahul.themoviedb.R
import com.rahul.themoviedb.common.ui.CircularLoader
import com.rahul.themoviedb.common.ui.ShowErrorDialog
import com.rahul.themoviedb.core.ui.UIState
import com.rahul.themoviedb.data.MovieDetailsData
import com.rahul.themoviedb.ui.theme.OrangeDark
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetails(movieId: Int, navController: NavController) {
    val viewModel: MovieDetailsViewModel = getViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val showDialog = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf("") }
    val loadData = remember { mutableStateOf(true) }

    LaunchedEffect(loadData) {
        if (loadData.value) {
            viewModel.getMovieDetails(movieId, "en")
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.movie_details_header),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
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
                    val successState = uiState as UIState.SuccessState<MovieDetailsData>
                    MovieDetailsView(successState.data)
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

@Preview(showBackground = true)
@Composable
fun MoviesDetailsPreview() {
    MovieDetails(1001, rememberNavController())
}