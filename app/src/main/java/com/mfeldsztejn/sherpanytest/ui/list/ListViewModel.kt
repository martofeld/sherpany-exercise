package com.mfeldsztejn.sherpanytest.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mfeldsztejn.sherpanytest.models.PostUserModel
import com.mfeldsztejn.sherpanytest.persitence.Database

class ListViewModel : ViewModel() {
    val postsLiveData: LiveData<List<PostUserModel>> by lazy {
        Database.getInstance()
                .postsDao()
                .getPostsWithUser()
    }
}
