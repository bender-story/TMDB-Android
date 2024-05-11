package com.rahul.themoviedb.features.list

import com.rahul.themoviedb.features.list.MoviesListViewModel
import com.rahul.themoviedb.core.ui.UIState
import com.rahul.themoviedb.data.Movie
import com.rahul.themoviedb.data.PopularMovies
import com.rahul.themoviedb.features.details.GetMovieDetailsUseCase
import com.rahul.themoviedb.features.details.repo.MovieDetailsRepository
import com.rahul.themoviedb.features.list.repo.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MoviesListViewModelTest {

    @Mock
    private lateinit var movieRepository: MovieRepository
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private lateinit var viewModel: MoviesListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
        viewModel = MoviesListViewModel(getPopularMoviesUseCase)
    }

    @Test
    fun `test getPopularMovies updates uiState correctly`() = runTest {
        val popularMovies = PopularMovies(1, listOf(), 1, 1)
        val expectedUIState = UIState.SuccessState(popularMovies)
        Mockito.`when`(movieRepository.getPopularMovies("en", 1))
            .thenReturn(popularMovies)

        viewModel.getPopularMovies("en", 1)
        advanceUntilIdle()  // Wait for all coroutine jobs to complete

        assertEquals(expectedUIState, viewModel.uiState.value)
    }
}