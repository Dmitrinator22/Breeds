package com.example.cutedogbreeds.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Breed (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name")val name: String,
    @ColumnInfo(name = "links")val links: String
)