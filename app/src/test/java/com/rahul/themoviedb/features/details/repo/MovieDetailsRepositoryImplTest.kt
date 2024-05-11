package com.rahul.themoviedb.features.details.repo

import com.rahul.themoviedb.core.network.ServiceInterface
import com.rahul.themoviedb.features.details.getMovieDetailsStub
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieDetailsRepositoryImplTest {

    @Mock
    private lateinit var serviceInterface: ServiceInterface
    private lateinit var movieDetailsRepository: MovieDetailsRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        movieDetailsRepository = MovieDetailsRepositoryImpl(serviceInterface)
    }

    @Test
    fun `test getMovieDetails returns data when serviceInterface returns data`() = runBlocking {
        val movieId = 1
        val language = "en"
        val expectedMovieDetails = getMovieDetailsStub()
        Mockito.`when`(serviceInterface.getMovieDetails(movieId, language))
            .thenReturn(expectedMovieDetails)

        val resultMovieDetails = movieDetailsRepository.getMovieDetails(movieId, language)

        assertEquals(expectedMovieDetails, resultMovieDetails)
    }
}