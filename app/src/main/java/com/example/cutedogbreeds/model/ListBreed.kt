package com.example.cutedogbreeds.model

import com.google.gson.annotations.SerializedName

data class ListBreed  (
    @SerializedName("message") val message : List<String>
    //@SerializedName("status") val status: String
)