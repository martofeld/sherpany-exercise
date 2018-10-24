package com.mfeldsztejn.sherpanytest.networking.apis

import com.mfeldsztejn.sherpanytest.networking.RetrofitInstanceBuilder
import com.mfeldsztejn.sherpanytest.networking.responses.PhotosList
import retrofit2.Call
import retrofit2.http.GET

interface PhotosApi {
    @GET("photos")
    fun photos(): Call<PhotosList>

    companion object {
        fun build(): PhotosApi = RetrofitInstanceBuilder.build().create(PhotosApi::class.java)
    }
}