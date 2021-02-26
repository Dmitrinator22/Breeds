package com.example.cutedogbreeds.model

import com.google.gson.annotations.SerializedName

data class AllBreeds(
        @SerializedName("message") val message : List<String>
)