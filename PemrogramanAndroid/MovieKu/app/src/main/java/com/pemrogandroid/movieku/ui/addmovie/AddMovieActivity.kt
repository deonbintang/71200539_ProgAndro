package com.pemrogandroid.movieku.ui.addmovie

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.pemrogandroid.movieku.databinding.ActivityAddMovieBinding
import com.pemrogandroid.movieku.model.Movie
import com.pemrogandroid.movieku.network.RetrofitClient.TMDB_IMAGEURL
import com.pemrogandroid.movieku.repository.LocalDataSource
import com.pemrogandroid.movieku.ui.search.SearchActivity
import com.squareup.picasso.Picasso

class AddMovieActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddMovieBinding

    private lateinit var activitySearchLauncher: ActivityResultLauncher<Intent>
    private lateinit var addViewModel: AddViewModel
    val selectedMovies = Movie()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //dependecy injection
        addViewModel = ViewModelProviders.of(this).get(AddViewModel::class.java)

        activitySearchLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                this@AddMovieActivity.runOnUiThread {
                    selectedMovies.id = result.data?.getIntExtra(SearchActivity.EXTRA_ID, -1)
                    selectedMovies.title = result.data?.getStringExtra(SearchActivity.EXTRA_TITLE)
                    selectedMovies.releaseDate = result.data?.getStringExtra(SearchActivity.EXTRA_RELEASE_DATE)
                    selectedMovies.posterPath = result.data?.getStringExtra(SearchActivity.EXTRA_POSTER_PATH)

                    binding.movieTitle.setText(selectedMovies.title)
                    binding.movieReleaseDate.setText(selectedMovies.releaseDate)
                    binding.movieImageview.tag = selectedMovies.posterPath
                    Picasso.get().load(TMDB_IMAGEURL + selectedMovies.posterPath)
                        .into(binding.movieImageview)
                }
            }
        }

        binding.searchButton.setOnClickListener({
            goToSearchMovieActivity()
        })

        binding.btnAddMovie.setOnClickListener({
            if (selectedMovies.id == null) {
                displayError("Movie belum dipilih")
            } else {
                //addMovie onClick
                addViewModel.addMovie(
                    selectedMovies.id!!,
                    selectedMovies.title!!,
                    selectedMovies.releaseDate!!,
                    selectedMovies.posterPath!!
                )
                returnToMain()
            }
        })
    }

    //search onClick
    fun goToSearchMovieActivity() {
        val title = binding.movieTitle.text.toString()
        val intent = Intent(this@AddMovieActivity, SearchActivity::class.java)
        intent.putExtra(SearchActivity.SEARCH_QUERY, title)
        activitySearchLauncher.launch(intent)
    }

    fun returnToMain() {
        setResult(RESULT_OK)
        finish()
    }

    fun displayMessage(message: String) {
        Toast.makeText(this@AddMovieActivity, message, Toast.LENGTH_LONG).show()
    }

    fun displayError(meesage: String) {
        displayMessage(meesage)
    }
}