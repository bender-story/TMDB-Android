package com.rahul.themoviedb.core.network

import com.rahul.themoviedb.Constants
import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

class RetrofitProvider(private val url: String) {

    val retrofit: Retrofit by lazy {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val headerInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${Constants.API_KEY}")
                .addHeader("accept", "application/json")
                .build()
            chain.proceed(request)
        }

        val cookieManager = CookieManager().apply {
            setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        }

        val okHttpClient = OkHttpClient.Builder()
            .cookieJar(JavaNetCookieJar(cookieManager))
            .addNetworkInterceptor(interceptor)
            .addInterceptor(headerInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}