package com.webage.lab03materialdesignsolution.starwars

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val _movieWrapper : MutableState<MovieWrapper> =
        mutableStateOf(MovieWrapper(0,null,null,emptyList<Movie>()))

    val movieWrapper get() = _movieWrapper

    fun getMovies() {
        viewModelScope.launch {
            val movieService = MovieService.getInstance()
            try {
                _movieWrapper.value = movieService.getMovies()
            } catch (e: Exception) {
                Log.wtf("GET_MOVIES",e.message)
            }
        }
    }

}