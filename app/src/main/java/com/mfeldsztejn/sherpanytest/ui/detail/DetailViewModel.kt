package com.mfeldsztejn.sherpanytest.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mfeldsztejn.sherpanytest.dtos.Post
import com.mfeldsztejn.sherpanytest.models.AlbumWithPhotos
import com.mfeldsztejn.sherpanytest.persitence.Database

class DetailViewModel : ViewModel() {

    var post: Post? = null
    val albumsLiveData: LiveData<List<AlbumWithPhotos>> by lazy {
        Database.getInstance().albumsDao().albumWithPhotosForUser(post!!.userId)
    }

    fun withPost(post: Post) {
        this.post = post
    }
}