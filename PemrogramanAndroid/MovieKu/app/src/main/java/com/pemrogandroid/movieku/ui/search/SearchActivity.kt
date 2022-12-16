package com.pemrogandroid.movieku.ui.search

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pemrogandroid.movieku.databinding.ActivitySearchMovieBinding
import com.pemrogandroid.movieku.model.Movie
import com.pemrogandroid.movieku.model.TmdbResponse
import com.pemrogandroid.movieku.repository.RemoteDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class SearchActivity : AppCompatActivity() {
    private val TAG = "SearchActivity"

    lateinit var binding: ActivitySearchMovieBinding
    private lateinit var adapter: SearchAdapter
    private lateinit var query: String
    private lateinit var searchViewModel: SearchViewModel

    companion object {
        val SEARCH_QUERY = "searchQuery"
        val EXTRA_ID = "SearchActivity.ID"
        val EXTRA_TITLE = "SearchActivity.TITLE_REPLY"
        val EXTRA_RELEASE_DATE = "SearchActivity.RELEASE_DATE_REPLY"
        val EXTRA_POSTER_PATH = "SearchActivity.POSTER_PATH_REPLY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        query = intent.getStringExtra(SEARCH_QUERY)!!
        binding.searchResultsRecyclerview.layoutManager = LinearLayoutManager(this)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        binding.progressBar.visibility = VISIBLE
        getSearchResult(query)
    }

    private fun getSearchResult(query: String) {
        searchViewModel.searchMovie(query).observe(this,{movies->
            displayResult(movies!!)
        })
    }

    fun displayResult(movies : List<Movie>) {
        binding.progressBar.visibility = INVISIBLE
        if (movies == null) {
            binding.searchResultsRecyclerview.visibility = INVISIBLE
            binding.noMoviesTextview.visibility = VISIBLE
        } else {
            adapter = SearchAdapter(
                movies,
                this@SearchActivity, itemListener
            )
            binding.searchResultsRecyclerview.adapter = adapter
            binding.searchResultsRecyclerview.visibility = VISIBLE
            binding.noMoviesTextview.visibility = INVISIBLE
        }
    }

    /**
     * Listener for clicks on tasks in the ListView.
     */
    internal var itemListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemClick(v: View, position: Int) {
            val movie = adapter.getItemAtPosition(position)

            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_ID, movie.id)
            replyIntent.putExtra(EXTRA_TITLE, movie.title)
            replyIntent.putExtra(EXTRA_RELEASE_DATE, movie.getReleaseYearFromDate())
            replyIntent.putExtra(EXTRA_POSTER_PATH, movie.posterPath)
            setResult(RESULT_OK, replyIntent)

            finish()
        }
    }

    interface RecyclerItemListener {
        fun onItemClick(v: View, position: Int)
    }
}