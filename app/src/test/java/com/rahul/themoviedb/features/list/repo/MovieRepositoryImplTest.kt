package com.rahul.themoviedb.features.list.repo

import com.rahul.themoviedb.core.network.ServiceInterface
import com.rahul.themoviedb.data.PopularMovies
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieRepositoryImplTest {

    @Mock
    private lateinit var serviceInterface: ServiceInterface
    private lateinit var movieRepository: MovieRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        movieRepository = MovieRepositoryImpl(serviceInterface)
    }

    @Test
    fun `test getPopularMovies returns data when serviceInterface returns data`() = runBlocking {
        val language = "en"
        val page = 1
        val expectedPopularMovies = PopularMovies(1, listOf(), 1, 1)
        Mockito.`when`(serviceInterface.getPopularMovies(language, page))
            .thenReturn(expectedPopularMovies)

        val resultPopularMovies = movieRepository.getPopularMovies(language, page)

        assertEquals(expectedPopularMovies, resultPopularMovies)
    }
}