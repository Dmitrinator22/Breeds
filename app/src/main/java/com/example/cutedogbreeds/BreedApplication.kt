package com.example.cutedogbreeds

import android.app.Application
import com.example.cutedogbreeds.db.BreedDatabase
import com.example.cutedogbreeds.repository.DbRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BreedApplication : Application() {

    val appScope = CoroutineScope(SupervisorJob())
    val database by lazy { BreedDatabase.getDatabase(this, appScope) }
    val repository by lazy { DbRepository(database.breedDao()) }

}