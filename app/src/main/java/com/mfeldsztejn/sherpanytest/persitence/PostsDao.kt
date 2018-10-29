package com.mfeldsztejn.sherpanytest.persitence

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.mfeldsztejn.sherpanytest.dtos.Post
import com.mfeldsztejn.sherpanytest.models.PostUserModel

@Dao
abstract class PostsDao : BaseDao<Post>() {

    @Query("SELECT * FROM posts")
    abstract fun get(): LiveData<List<Post>>

    @Query("SELECT posts.*, users.* FROM posts INNER JOIN users ON posts.userId == users.user_id")
    abstract fun getPostsWithUser(): DataSource.Factory<Int, PostUserModel>

    @Query("SELECT posts.*, users.* FROM posts INNER JOIN users ON posts.userId == users.user_id WHERE posts.title LIKE :title")
    abstract fun getPostsWithUserWithTitleLike(title: String): DataSource.Factory<Int, PostUserModel>

    @Query("SELECT * FROM posts where id=:id")
    abstract override fun find(id: Int): Post?
}