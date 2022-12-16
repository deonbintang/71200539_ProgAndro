package com.pemrogandroid.movieku.ui.moviedetails

import com.pemrogandroid.movieku.model.Movie
import com.pemrogandroid.movieku.model.MovieDetailsResponse
import java.util.HashSet


class MovieDetailsContract {
  interface PresenterInterface {
    fun getMovieDetails(id: Int)
    fun stop()
  }

  interface ViewInterface {
    fun displayDetails(movieDetail: MovieDetailsResponse)
    fun displayMessage(message: String)
    fun displayError(message: String)
  }
}
