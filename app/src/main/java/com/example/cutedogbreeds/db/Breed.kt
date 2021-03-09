package com.example.cutedogbreeds.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed_table")
data class Breed (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "breed")val breed: String,
    @ColumnInfo(name = "links")val links: String
)
/*
@Entity(tableName = "link_table")
data class Link (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val link: String,
    @ColumnInfo val breed: String
)


 */
