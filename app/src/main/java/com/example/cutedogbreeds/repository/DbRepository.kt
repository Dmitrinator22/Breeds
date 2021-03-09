package com.example.cutedogbreeds.repository


import androidx.annotation.WorkerThread

import com.example.cutedogbreeds.db.Breed
import com.example.cutedogbreeds.db.BreedDao
//import com.example.cutedogbreeds.db.Link

import kotlinx.coroutines.flow.Flow

/*
import kotlinx.coroutines.flow.Flow
import androidx.constraintlayout.solver.widgets.Flow
import androidx.constraintlayout.helper.widget.Flow
import org.intellij.lang.annotations.Flow


 */

class DbRepository (private val breedDao: BreedDao) {

    val allBreeds: Flow<List<Breed>> = breedDao.getAll()

    @Suppress("RedundantSuspendModidier")
    @WorkerThread
    suspend fun insert(breed: Breed){
        breedDao.insert(breed)
    }

    suspend fun deleteAll(){
        breedDao.deleteAll()
    }

    suspend fun getOneBreed(name: String): Breed{
        return breedDao.getOneBreed(name)
    }

    suspend fun deleteBreed(name: String){
        breedDao.deleteBreed(name)
    }

/*
    suspend fun insertLink(link: Link){
        breedDao.insertLink(link)
    }


 */


}