package com.rahul.themoviedb.di

import com.rahul.themoviedb.Constants
import com.rahul.themoviedb.features.details.MovieDetailsViewModel
import com.rahul.themoviedb.features.list.MoviesListViewModel
import com.rahul.themoviedb.core.network.RetrofitProvider
import com.rahul.themoviedb.core.network.ServiceInterface
import com.rahul.themoviedb.core.usecase.UseCase
import com.rahul.themoviedb.features.list.GetPopularMoviesUseCase
import com.rahul.themoviedb.features.list.repo.MovieRepository
import com.rahul.themoviedb.features.list.repo.MovieRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RetrofitProvider(Constants.BASE_URL) }
    single<ServiceInterface> { get<RetrofitProvider>().retrofit.create(ServiceInterface::class.java) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single { GetPopularMoviesUseCase(get()) }
    viewModel { MoviesListViewModel(get()) }
    viewModel { MovieDetailsViewModel() }
}