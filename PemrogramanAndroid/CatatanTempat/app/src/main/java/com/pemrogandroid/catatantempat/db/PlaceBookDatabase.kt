package com.pemrogandroid.catatantempat.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pemrogandroid.catatantempat.model.Bookmark

@Database(entities = arrayOf(Bookmark::class), version = 2)
abstract class PlaceBookDatabase : RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDao

    companion object {

        private var instance: PlaceBookDatabase? = null

        fun getInstance(context: Context): PlaceBookDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlaceBookDatabase::class.java, "PlaceBook"
                )
                    //prevent crash by telling Room to create the new DB from scratch & discard all old Data
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as PlaceBookDatabase
        }
    }
}