package com.pemrogandroid.movieku.ui.moviedetails

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pemrogandroid.movieku.databinding.ActivityMovieDetailsBinding
import com.pemrogandroid.movieku.model.MovieDetailsResponse
import com.pemrogandroid.movieku.model.TmdbResponse
import com.pemrogandroid.movieku.network.RetrofitClient
import com.pemrogandroid.movieku.repository.RemoteDataSource
import com.pemrogandroid.movieku.ui.mainmenu.MainActivity
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsContract.ViewInterface {
    private val TAG = "MovieDetailsActivity"

    lateinit var binding: ActivityMovieDetailsBinding
    var selectedMovieId: Int = 0

    lateinit var presenter: MovieDetailsContract.PresenterInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MovieDetailsPresenter(this, RemoteDataSource())
        selectedMovieId = intent.getIntExtra(MainActivity.EXTRA_ID, -1)
    }

    override fun onStart() {
        super.onStart()
        presenter.getMovieDetails(selectedMovieId)
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun displayDetails(movieDetail: MovieDetailsResponse) {
        binding.titleTextview.text = movieDetail.title
        binding.overviewOverview.text = movieDetail.overview
        binding.releaseDateTextview.text = movieDetail.releaseDate
        Picasso.get().load(RetrofitClient.TMDB_IMAGEURL + movieDetail.posterPath)
            .into(binding.movieImageview)
    }

    override fun displayMessage(message: String) {
        Toast.makeText(this@MovieDetailsActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun displayError(message: String) {
        displayMessage(message)
    }
}