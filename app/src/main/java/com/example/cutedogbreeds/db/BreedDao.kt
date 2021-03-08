package com.example.cutedogbreeds.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedDao {

    @Query("SELECT * FROM breed")
    fun getAll(): Flow<List<Breed>>

    @Query("SELECT * FROM breed WHERE id LIKE :id")
    suspend fun getOneId(id: Int): Breed

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert (breed : Breed)


    @Query("DELETE FROM breed")
    suspend fun deleteAll()



    }