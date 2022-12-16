package com.pemrogandroid.movieku.ui.moviedetails

import android.util.Log
import android.widget.Toast
import com.pemrogandroid.movieku.model.Movie
import com.pemrogandroid.movieku.model.MovieDetailsResponse
import com.pemrogandroid.movieku.repository.LocalDataSource
import com.pemrogandroid.movieku.repository.RemoteDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


class MovieDetailsPresenter(
    private var viewInterface: MovieDetailsContract.ViewInterface,
    private var dataSource: RemoteDataSource
) : MovieDetailsContract.PresenterInterface {
    private val TAG = "MovieDetailsPresenter"
    private val compositeDisposable = CompositeDisposable()

    val getMovieDetailsObservable: (Int) -> Observable<MovieDetailsResponse> =
        { id -> dataSource.movieDetailsObservable(id) }

    val observer: DisposableObserver<MovieDetailsResponse>
        get() = object : DisposableObserver<MovieDetailsResponse>() {

            override fun onNext(@NonNull movieDetail: MovieDetailsResponse) {
                viewInterface.displayDetails(movieDetail)
            }

            override fun onError(@NonNull e: Throwable) {
                viewInterface.displayError("Error fetching Movie Data:" + e.message)
            }

            override fun onComplete() {
                Log.d(TAG, "Completed")
            }
        }


    override fun getMovieDetails(id: Int) {
        val searchResultsDisposable = getMovieDetailsObservable(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer)

        compositeDisposable.add(searchResultsDisposable)
    }

    override fun stop() {
        compositeDisposable.clear()
    }
}
