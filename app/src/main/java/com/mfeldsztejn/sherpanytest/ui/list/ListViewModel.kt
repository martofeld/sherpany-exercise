package com.mfeldsztejn.sherpanytest.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mfeldsztejn.sherpanytest.models.PostUserModel
import com.mfeldsztejn.sherpanytest.persitence.Database

class ListViewModel : ViewModel() {
    val postsLiveData: LiveData<PagedList<PostUserModel>>

    init {
        val factory = Database.getInstance()
                .postsDao()
                .getPostsWithUser()

        val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .build()

        postsLiveData = LivePagedListBuilder<Int, PostUserModel>(factory, config).build()
    }
}
