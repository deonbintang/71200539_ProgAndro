package com.pemrogandroid.catatantempat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pemrogandroid.catatantempat.R
import com.pemrogandroid.catatantempat.databinding.BookmarkItemBinding
import com.pemrogandroid.catatantempat.ui.MapsActivity
import com.pemrogandroid.catatantempat.viewmodel.MapsViewModel.BookmarkMarkerView

class BookmarkListAdapter(
    private var bookmarkData: List<BookmarkMarkerView>?,
    private val mapsActivity: MapsActivity
) : RecyclerView.Adapter<BookmarkListAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: BookmarkItemBinding,
        private val mapsActivity: MapsActivity
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {

            }
        }
    }

    fun setBookmarkData(bookmarks: List<BookmarkMarkerView>) {
        this.bookmarkData = bookmarks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BookmarkItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, mapsActivity)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bookmarkData?.let { list->
            val bookmarkViewData = list[position]
            holder.binding.root.tag = bookmarkViewData
            holder.binding.bookmarkData = bookmarkViewData
            holder.binding.bookmarkIcon.setImageResource(R.drawable.ic_other)
        }
    }

    override fun getItemCount() = bookmarkData?.size ?: 0