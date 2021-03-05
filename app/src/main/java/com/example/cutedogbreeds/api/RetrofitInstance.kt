package com.example.cutedogbreeds.api


import com.example.cutedogbreeds.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    val retrofit:Retrofit.Builder by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    }

    val api: SimpleApi by lazy {
        retrofit
            .build()
            .create(SimpleApi::class.java)

    }

}