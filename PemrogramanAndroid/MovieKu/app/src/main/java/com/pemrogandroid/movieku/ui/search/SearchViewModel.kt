package com.pemrogandroid.movieku.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pemrogandroid.movieku.model.Movie
import com.pemrogandroid.movieku.repository.RemoteDataSource

class SearchViewModel(private val remoteRepository : RemoteDataSource = RemoteDataSource()): ViewModel() {
    fun searchMovie(query : String): MutableLiveData<List<Movie>?> {
        return remoteRepository.searchResultObserveable(query)
    }
}