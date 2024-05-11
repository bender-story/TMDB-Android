package com.rahul.themoviedb.core.usecase

import com.rahul.themoviedb.core.ui.UIState

interface UseCase<Params> {
    suspend fun execute(params: Params): UIState
}