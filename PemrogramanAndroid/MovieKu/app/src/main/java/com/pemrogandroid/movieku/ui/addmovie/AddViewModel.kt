package com.pemrogandroid.movieku.ui.addmovie

import androidx.lifecycle.ViewModel
import com.pemrogandroid.movieku.model.Movie
import com.pemrogandroid.movieku.repository.LocalDataSource

class AddViewModel(private val localRepository: LocalDataSource = LocalDataSource()) : ViewModel() {
    fun addMovie(id: Int, title: String, releaseDate: String, posterPath: String) {
        val movie = Movie(id, title, releaseDate, posterPath)
        localRepository.insert(movie)
    }
}