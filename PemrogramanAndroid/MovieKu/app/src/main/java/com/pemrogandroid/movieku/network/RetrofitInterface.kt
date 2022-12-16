package com.pemrogandroid.movieku.network

import com.pemrogandroid.movieku.model.MovieDetailsResponse
import com.pemrogandroid.movieku.model.TmdbResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("search/movie")
    fun searchMovie(@Query("api_key") api_key: String, @Query("query") query: String)
            : Call<TmdbResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int, @Query("api_key") api_key: String): Observable<MovieDetailsResponse>
}