package com.mfeldsztejn.sherpanytest.networking.apis

import com.mfeldsztejn.sherpanytest.dtos.Post
import com.mfeldsztejn.sherpanytest.networking.RetrofitInstanceBuilder
import retrofit2.Call
import retrofit2.http.GET

interface PostsApi {
    @GET("posts")
    fun posts(): Call<List<Post>>

    companion object {
        fun build(): PostsApi = RetrofitInstanceBuilder.build().create(PostsApi::class.java)
    }
}