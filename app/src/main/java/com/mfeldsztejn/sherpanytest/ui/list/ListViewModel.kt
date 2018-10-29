package com.mfeldsztejn.sherpanytest.ui.list

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mfeldsztejn.sherpanytest.models.PostUserModel
import com.mfeldsztejn.sherpanytest.persitence.Database

class ListViewModel : ViewModel() {
    var postsLiveData: LiveData<PagedList<PostUserModel>>
    private val config: PagedList.Config

    init {
        val factory = Database.getInstance()
                .postsDao()
                .getPostsWithUser()

        config = PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .build()

        postsLiveData = LivePagedListBuilder<Int, PostUserModel>(factory, config).build()
    }

    fun filter(lifecycleOwner: LifecycleOwner, filterText: String?) {

        val dao = Database.getInstance()
                .postsDao()
        val factory = if (filterText.isNullOrEmpty()) dao.getPostsWithUser() else dao.getPostsWithUserWithTitleLike("%$filterText%")

        postsLiveData.removeObservers(lifecycleOwner)
        postsLiveData = LivePagedListBuilder<Int, PostUserModel>(factory, config).build()
    }
}
