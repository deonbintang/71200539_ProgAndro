package com.pemrogandroid.catatantempat.viewmodel

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.pemrogandroid.catatantempat.model.Bookmark
import com.pemrogandroid.catatantempat.repository.BookmarkRepo
import com.pemrogandroid.catatantempat.util.ImageUtils

class MapsViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "MapsViewModel"

    private var bookmarkRepo: BookmarkRepo = BookmarkRepo(
        getApplication()
    )
    private var bookmarks: LiveData<List<BookmarkMarkerView>>? = null

    fun addBookmarkFromPlace(place: Place, image: Bitmap?) {

        val bookmark = bookmarkRepo.createBookmark()
        bookmark.placeId = place.id
        bookmark.name = place.name.toString()
        bookmark.longitude = place.latLng?.longitude ?: 0.0
        bookmark.latitude = place.latLng?.latitude ?: 0.0
        bookmark.phone = place.phoneNumber.toString()
        bookmark.address = place.address.toString()

        val newId = bookmarkRepo.addBookmark(bookmark)
        Log.i(TAG, "New bookmark $newId added to the database.")

        image?.let { bookmark.setImage(it, getApplication()) }
    }

    fun getBookmarkMarkerViews():
            LiveData<List<BookmarkMarkerView>>? {
        if (bookmarks == null) {
            mapBookmarksToMarkerView()
        }
        return bookmarks
    }

    private fun mapBookmarksToMarkerView() {
        bookmarks = Transformations.map(bookmarkRepo.allBookmarks) { repoBookmarks ->
            repoBookmarks.map { bookmark ->
                bookmarkToMarkerView(bookmark)
            }
        }
    }

    private fun bookmarkToMarkerView(bookmark: Bookmark) = BookmarkMarkerView(
        bookmark.id,
        bookmark.name,
        bookmark.phone,
        LatLng(bookmark.latitude, bookmark.longitude)
    )

    data class BookmarkMarkerView(
        var id: Long? = null,
        var name: String? = "",
        var phone: String = "",
        var location: LatLng = LatLng(0.0, 0.0)
    ) {
        fun getImage(context: Context) = id?.let {
            ImageUtils.loadBitmapFromFile(context, Bookmark.generateImageFilename(it))
        }
    }
}