package com.mfeldsztejn.sherpanytest.persitence

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mfeldsztejn.sherpanytest.dtos.Album
import com.mfeldsztejn.sherpanytest.dtos.Photo
import com.mfeldsztejn.sherpanytest.dtos.Post
import com.mfeldsztejn.sherpanytest.dtos.User

@androidx.room.Database(entities = [User::class, Post::class, Album::class, Photo::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "database"
        private var database: Database? = null

        fun init(context: Context) {
            database = Room
                    .databaseBuilder(context, Database::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
        }

        fun getInstance(): Database {
            if (database == null) {
                throw IllegalStateException("Attempting to access uninitialized DB. Did you call init?")
            }
            return database!!
        }
    }

    abstract fun usersDao(): UsersDao
    abstract fun postsDao(): PostsDao
    abstract fun albumsDao(): AlbumsDao
    abstract fun photosDao(): PhotosDao
}