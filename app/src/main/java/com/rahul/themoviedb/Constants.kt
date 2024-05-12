package com.rahul.themoviedb

object Constants {
    init {
        System.loadLibrary("native-lib")
    }
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    external fun getApiKey(): String
}