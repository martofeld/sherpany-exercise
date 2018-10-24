package com.mfeldsztejn.sherpanytest.persitence

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.mfeldsztejn.sherpanytest.dtos.Post
import com.mfeldsztejn.sherpanytest.models.PostUserModel

@Dao
interface PostsDao : BaseDao<Post> {

    @Query("SELECT * FROM posts")
    fun get(): LiveData<List<Post>>

    @Query("SELECT posts.*, users.* FROM posts INNER JOIN users ON posts.userId == users.user_id")
    fun getPostsWithUser(): DataSource.Factory<Int, PostUserModel>
}