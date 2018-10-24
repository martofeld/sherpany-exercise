package com.mfeldsztejn.sherpanytest.networking.apis

import com.mfeldsztejn.sherpanytest.networking.RetrofitInstanceBuilder
import com.mfeldsztejn.sherpanytest.networking.responses.AlbumsList
import retrofit2.Call
import retrofit2.http.GET

interface AlbumsApi {
    @GET("albums")
    fun albums(): Call<AlbumsList>

    companion object {
        fun build(): AlbumsApi = RetrofitInstanceBuilder.build().create(AlbumsApi::class.java)
    }
}