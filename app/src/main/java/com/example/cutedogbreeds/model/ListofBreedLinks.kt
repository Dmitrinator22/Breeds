package com.example.cutedogbreeds.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListofBreedLinks  (
        @Expose
        @SerializedName("message") val message : List<String>?
        //@SerializedName("status") val status: String
)