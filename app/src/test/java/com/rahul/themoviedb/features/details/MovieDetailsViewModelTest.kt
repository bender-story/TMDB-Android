package com.rahul.themoviedb.features.details

import com.rahul.themoviedb.core.ui.UIState
import com.rahul.themoviedb.data.Genre
import com.rahul.themoviedb.data.MovieDetailsData
import com.rahul.themoviedb.data.ProductionCompany
import com.rahul.themoviedb.data.ProductionCountry
import com.rahul.themoviedb.data.SpokenLanguage
import com.rahul.themoviedb.features.details.repo.MovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

    @Mock
    private lateinit var movieDetailsRepository: MovieDetailsRepository
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    private lateinit var viewModel: MovieDetailsViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        getMovieDetailsUseCase = GetMovieDetailsUseCase(movieDetailsRepository)
        viewModel = MovieDetailsViewModel(getMovieDetailsUseCase)
    }

    @Test
    fun `getMovieDetails returns SuccessState when use case is successful`() = runTest {
        // Arrange
        val movieId = 1
        val language = "en"
        val expectedMovieDetails = getMovieDetailsStub()
        val expectedUIState = UIState.SuccessState(expectedMovieDetails)
        Mockito.`when`(getMovieDetailsUseCase.execute(GetMovieDetailsUseCase.Params(movieId, language)))
            .thenReturn(expectedUIState)

        // Act
        viewModel.getMovieDetails(movieId, language)
        advanceUntilIdle()  // Wait for all coroutine jobs to complete

        // Assert
        val actualState = viewModel.uiState.value
        Assert.assertEquals(expectedUIState, actualState)
    }

    @Test
    fun `getMovieDetails returns ErrorState when use case throws exception`() = runTest {
        // Arrange
        val movieId = 1
        val language = "en"
        val exceptionMessage = "Test exception"
        Mockito.`when`(getMovieDetailsUseCase.execute(GetMovieDetailsUseCase.Params(movieId, language)))
            .thenThrow(RuntimeException(exceptionMessage))

        // Act
        viewModel.getMovieDetails(movieId, language)
        advanceUntilIdle()  // Ensures all coroutines have completed

        // Assert
        val actualState = viewModel.uiState.value
        Assert.assertTrue(actualState is UIState.ErrorState && exceptionMessage == (actualState as UIState.ErrorState).errorMessage)
    }
}
