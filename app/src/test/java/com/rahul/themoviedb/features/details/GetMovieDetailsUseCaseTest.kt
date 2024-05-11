package com.rahul.themoviedb.features.details

import com.rahul.themoviedb.data.Genre
import com.rahul.themoviedb.data.MovieDetailsData
import com.rahul.themoviedb.data.ProductionCompany
import com.rahul.themoviedb.data.ProductionCountry
import com.rahul.themoviedb.data.SpokenLanguage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import com.rahul.themoviedb.core.ui.UIState
import com.rahul.themoviedb.features.details.repo.MovieDetailsRepository
import com.rahul.themoviedb.features.list.GetPopularMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GetMovieDetailsUseCaseTest {

    @Mock
    private lateinit var movieDetailsRepository: MovieDetailsRepository
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        getMovieDetailsUseCase = GetMovieDetailsUseCase(movieDetailsRepository)
    }

    @Test
    fun `test execute returns SuccessState when repository returns data`() = runBlockingTest {
       val movieDetails = getMovieDetailsStub()
        val expectedUIState = UIState.SuccessState(movieDetails)
        Mockito.`when`(movieDetailsRepository.getMovieDetails(1, "en"))
            .thenReturn(movieDetails)

        val resultUIState = getMovieDetailsUseCase.execute(GetMovieDetailsUseCase.Params(1, "en"))

        assertEquals(expectedUIState, resultUIState)
    }

    @Test
    fun `test execute returns ErrorState when repository throws exception`() = runBlockingTest {
        val exception = RuntimeException("Test exception")
        Mockito.`when`(movieDetailsRepository.getMovieDetails(1, "en"))
            .thenThrow(exception)

        val resultUIState = getMovieDetailsUseCase.execute(GetMovieDetailsUseCase.Params(1, "en"))

        assertTrue(resultUIState is UIState.ErrorState)
        assertEquals(exception.message, (resultUIState as UIState.ErrorState).errorMessage)
    }
}