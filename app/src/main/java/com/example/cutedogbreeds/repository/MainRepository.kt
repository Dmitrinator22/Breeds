package com.example.cutedogbreeds.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.cutedogbreeds.api.RetrofitInstance
import com.example.cutedogbreeds.model.AllBreeds
import com.example.cutedogbreeds.model.ListofBreedLinks
import kotlinx.coroutines.*


object MainRepository {

    var jobAll : CompletableJob? = null
    var jobList : CompletableJob? = null

    fun getAllBreeds(): LiveData<AllBreeds>{
        jobAll = Job()

        return object : LiveData<AllBreeds>(){
            override fun onActive() {
                super.onActive()
                jobAll?.let {
                    CoroutineScope(Dispatchers.IO+ it).launch {
                        val list = RetrofitInstance.api.getAllBreeds()
                        withContext(Dispatchers.Main){
                            Log.e("MainRepository", "AllBreeds Job Done")
                            value = list
                            it.complete()
                        }

                    }
                }

            }
        }

    }

    fun getBreedsList(breed : String): LiveData<ListofBreedLinks>{

        jobList=Job()
        return object : LiveData<ListofBreedLinks>(){
            override fun onActive() {
                super.onActive()
                jobList?.let {
                    CoroutineScope(Dispatchers.IO + it).launch {
                        val list = RetrofitInstance.api.getListofBreed(breed)
                        withContext(Dispatchers.Main){
                            Log.e("MainRepository", "List Job Done")
                            value = list
                            it.complete()
                        }

                    }
                }

            }

        }

    }

    fun cancelJobs(){
        jobAll?.cancel()
        jobList?.cancel()
    }

}