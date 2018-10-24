package com.mfeldsztejn.sherpanytest.networking.apis

import com.mfeldsztejn.sherpanytest.dtos.User
import com.mfeldsztejn.sherpanytest.networking.RetrofitInstanceBuilder
import retrofit2.Call
import retrofit2.http.GET

interface UsersApi {
    @GET("users")
    fun users(): Call<List<User>>

    companion object {
        fun build(): UsersApi = RetrofitInstanceBuilder.build().create(UsersApi::class.java)
    }
}