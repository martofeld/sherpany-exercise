package com.mfeldsztejn.sherpanytest.networking.apis

import com.mfeldsztejn.sherpanytest.networking.RetrofitInstanceBuilder
import com.mfeldsztejn.sherpanytest.networking.responses.PostsList
import retrofit2.Call
import retrofit2.http.GET

interface PostsApi {
    @GET("posts")
    fun posts(): Call<PostsList>

    companion object {
        fun build(): PostsApi = RetrofitInstanceBuilder.build().create(PostsApi::class.java)
    }
}