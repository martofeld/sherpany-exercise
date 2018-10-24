package com.mfeldsztejn.sherpanytest

import android.app.Application
import android.os.AsyncTask
import com.mfeldsztejn.sherpanytest.networking.apis.AlbumsApi
import com.mfeldsztejn.sherpanytest.networking.apis.PhotosApi
import com.mfeldsztejn.sherpanytest.networking.apis.PostsApi
import com.mfeldsztejn.sherpanytest.networking.apis.UsersApi
import com.mfeldsztejn.sherpanytest.persitence.Database

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Database.init(this)

        AsyncTask.execute {
            // Run all request on background thread and pass result to save
            Database.getInstance().usersDao().insertAll(UsersApi.build().users().execute().body()!!)
            Database.getInstance().postsDao().insertAll(PostsApi.build().posts().execute().body()!!)
            Database.getInstance().albumsDao().insertAll(AlbumsApi.build().albums().execute().body()!!)
            Database.getInstance().photosDao().insertAll(PhotosApi.build().photos().execute().body()!!)
            // TODO: Handle errors
        }

    }
}