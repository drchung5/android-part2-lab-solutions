package com.webage.lab03materialdesignsolution.starwars

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://swapi.dev/api/"

interface  MovieService {

    @GET("films")
    suspend fun getMovies() : MovieWrapper

    companion object {
        var movieService :MovieService ?= null
        fun getInstance() :MovieService {
            if( movieService == null ) {
                movieService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieService::class.java)
            }
            return movieService!!
        }
    }
}
