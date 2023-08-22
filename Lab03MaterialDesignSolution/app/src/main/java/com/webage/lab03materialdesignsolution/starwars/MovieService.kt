package com.webage.lab03materialdesignsolution.starwars

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


const val BASE_URL = "https://swapi.dev/api/"

interface  MovieService {

    @GET("films")
    suspend fun getMovies() : MovieWrapper



    companion object {

        val okHttpClient: OkHttpClient
            get() = OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .build()

        var movieService :MovieService ?= null

        fun getInstance() :MovieService {
            if( movieService == null ) {
                movieService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                    .create(MovieService::class.java)
            }
            return movieService!!
        }

    }
}
