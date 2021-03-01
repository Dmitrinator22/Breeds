package com.example.cutedogbreeds.api


import com.example.cutedogbreeds.Dog
import com.example.cutedogbreeds.model.AllBreeds
import com.example.cutedogbreeds.model.ListBreed
import com.example.cutedogbreeds.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {

    @GET("/api/breeds/image/random")
    suspend fun getPost(): Post

    @GET("/api/breed/pitbull/images")
    suspend fun getListBreed(): ListBreed

    @GET("/api/breeds/list")
    suspend fun getAllBreeds(): AllBreeds

    @GET("/api/breed/{dog}/images")
    suspend fun getListofBreed(@Path("dog") dog : String): ListBreed


}