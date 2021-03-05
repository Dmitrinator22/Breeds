package com.example.cutedogbreeds.repository

import com.example.cutedogbreeds.api.RetrofitInstance
import com.example.cutedogbreeds.model.AllBreeds
import com.example.cutedogbreeds.model.ListBreed
import com.example.cutedogbreeds.model.ListofBreedLinks
import com.example.cutedogbreeds.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post> {

        return RetrofitInstance.api.getPost()

    }

    suspend fun getBreed(): Response<ListBreed> {
        return  RetrofitInstance.api.getBreed()

    }
/*
    suspend fun getAllBreeds():Response<AllBreeds>{
        return RetrofitInstance.api.getAllBreeds()
    }

    suspend fun getListofBreed(dog: String):Response<ListofBreedLinks>{
        return RetrofitInstance.api.getListofBreed(dog)
    }
*/
}