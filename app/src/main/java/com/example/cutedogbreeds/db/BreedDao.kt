package com.example.cutedogbreeds.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedDao {

    @Query("SELECT * FROM breed_table ORDER BY breed ASC")
    fun getAll(): Flow<List<Breed>>

    @Query("SELECT * FROM breed_table WHERE breed LIKE :breed")
    suspend fun getOneBreed(breed: String): Breed

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert (breed : Breed)

    @Query("DELETE FROM breed_table")
    suspend fun deleteAll()

    @Query("DELETE FROM breed_table WHERE breed LIKE :breed")
    suspend fun deleteBreed(breed: String)

/*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLink(link: Link)

    @Transaction
    @Query("SELECT * FROM breed_table")
    suspend fun getLink(): List<Link>



 */

    }