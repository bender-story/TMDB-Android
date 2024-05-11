package com.rahul.themoviedb.features.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.rahul.themoviedb.data.PopularMovies
import com.rahul.themoviedb.ui.theme.OrangeDark
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesList(navController: NavController) {
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
                    PopularMoviesList(successState.data.results, listState, navController)
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
fun MoviesListPreview() {
    MoviesList(rememberNavController())
}