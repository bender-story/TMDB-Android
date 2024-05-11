package com.rahul.themoviedb.features.list

import com.rahul.themoviedb.core.ui.UIState
import com.rahul.themoviedb.data.PopularMovies
import com.rahul.themoviedb.features.list.repo.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GetPopularMoviesUseCaseTest {

    @Mock
    private lateinit var movieRepository: MovieRepository
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
    }

    @Test
    fun `test execute returns SuccessState when repository returns data`() = runTest {
        val popularMovies = PopularMovies(1, listOf(), 1, 1)
        val expectedUIState = UIState.SuccessState(popularMovies)
        Mockito.`when`(movieRepository.getPopularMovies("en", 1))
            .thenReturn(popularMovies)

        val resultUIState = getPopularMoviesUseCase.execute(GetPopularMoviesUseCase.Params("en", 1))

        assertEquals(expectedUIState, resultUIState)
    }

    @Test
    fun `test execute returns ErrorState when repository throws exception`() = runTest {
        val exception = RuntimeException("Test exception")
        Mockito.`when`(movieRepository.getPopularMovies("en", 1))
            .thenThrow(exception)

        val resultUIState = getPopularMoviesUseCase.execute(GetPopularMoviesUseCase.Params("en", 1))

        assertTrue(resultUIState is UIState.ErrorState)
        assertEquals(exception.message, (resultUIState as UIState.ErrorState).errorMessage)
    }
}