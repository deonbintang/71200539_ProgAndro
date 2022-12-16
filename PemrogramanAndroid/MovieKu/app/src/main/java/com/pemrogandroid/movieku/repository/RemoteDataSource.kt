package com.pemrogandroid.movieku.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pemrogandroid.movieku.MovieKuApp
import com.pemrogandroid.movieku.R
import com.pemrogandroid.movieku.model.Movie
import com.pemrogandroid.movieku.model.MovieDetailsResponse
import com.pemrogandroid.movieku.model.TmdbResponse
import com.pemrogandroid.movieku.network.RetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RemoteDataSource {

    fun searchResultObserveable(query: String): MutableLiveData<List<Movie>?> {
        val data = MutableLiveData<List<Movie>?>()
        RetrofitClient.movieApi
            .searchMovie(MovieKuApp.instance.resources.getString(R.string.TMDB_API_KEY), query)
            .enqueue(object : Callback<TmdbResponse> {
                override fun onResponse(call: Call<TmdbResponse>, response: Response<TmdbResponse>) {
                    data.value = response.body()?.results
                    Log.d(this.javaClass.simpleName, "onResponse: succeed")
                }

                override fun onFailure(call: Call<TmdbResponse>, t: Throwable) {
                    data.value = null
                    Log.d(this.javaClass.simpleName, "onResponse: faill")
                }
            })

        return data
    }

    fun movieDetailsObservable(id: Int): Observable<MovieDetailsResponse> {
        return RetrofitClient.movieApi
            .getMovieDetails(id, MovieKuApp.instance.resources.getString(R.string.TMDB_API_KEY))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}