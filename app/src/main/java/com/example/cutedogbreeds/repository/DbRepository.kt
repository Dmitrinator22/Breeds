package com.example.cutedogbreeds.repository


import androidx.annotation.WorkerThread

import com.example.cutedogbreeds.db.Breed
import com.example.cutedogbreeds.db.BreedDao
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


}