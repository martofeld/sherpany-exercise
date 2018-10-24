package com.mfeldsztejn.sherpanytest.networking.apis

import com.mfeldsztejn.sherpanytest.dtos.Photo
import com.mfeldsztejn.sherpanytest.networking.RetrofitInstanceBuilder
import retrofit2.Call
import retrofit2.http.GET

interface PhotosApi {
    @GET("photos")
    fun photos(): Call<List<Photo>>

    companion object {
        fun build(): PhotosApi = RetrofitInstanceBuilder.build().create(PhotosApi::class.java)
    }
}