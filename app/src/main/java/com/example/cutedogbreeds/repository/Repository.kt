package com.example.cutedogbreeds.repository

import com.example.cutedogbreeds.api.RetrofitInstance
import com.example.cutedogbreeds.model.ListBreed
import com.example.cutedogbreeds.model.Post

class Repository {

    suspend fun getPost(): Post {

        return RetrofitInstance.api.getPost()

    }

    suspend fun getListBreed(): ListBreed {
        return  RetrofitInstance.api.getListBreed()

    }

}