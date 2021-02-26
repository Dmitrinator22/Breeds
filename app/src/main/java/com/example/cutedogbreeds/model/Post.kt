package com.example.cutedogbreeds.model

import com.google.gson.annotations.SerializedName

data class Post (
    @SerializedName("message") val message : String,
    @SerializedName("status") val status: String
)