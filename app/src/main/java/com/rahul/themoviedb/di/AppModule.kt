package com.rahul.themoviedb.di

import com.rahul.themoviedb.Constants
import com.rahul.themoviedb.feature.details.MovieDetailsViewModel
import com.rahul.themoviedb.feature.list.MoviesListViewModel
import com.rahul.themoviedb.network.RetrofitProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RetrofitProvider(Constants.BASE_URL) }
    viewModel { MoviesListViewModel() }
    viewModel { MovieDetailsViewModel() }
}