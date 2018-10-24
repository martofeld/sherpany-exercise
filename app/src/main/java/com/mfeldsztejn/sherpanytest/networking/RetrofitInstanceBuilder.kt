package com.mfeldsztejn.sherpanytest.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceBuilder {
    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com"

        fun build(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}