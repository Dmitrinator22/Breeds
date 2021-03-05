package com.example.cutedogbreeds

import android.util.Log
import androidx.lifecycle.*
import com.example.cutedogbreeds.model.AllBreeds
import com.example.cutedogbreeds.model.ListBreed
import com.example.cutedogbreeds.model.ListofBreedLinks
import com.example.cutedogbreeds.model.Post
import com.example.cutedogbreeds.repository.MainRepository
import com.example.cutedogbreeds.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel() : ViewModel(){
    //private val repository: Repository
    val myRandom: MutableLiveData<Response<Post>> = MutableLiveData()
    val myBreed: MutableLiveData<Response<ListBreed>> = MutableLiveData()
    val myBreedsAll: MutableLiveData<Response<AllBreeds>> = MutableLiveData()
    val myWantedBreedList: MutableLiveData<Response<ListofBreedLinks>> = MutableLiveData()

    private val breed: MutableLiveData<String> = MutableLiveData()

    var listofall: LiveData<AllBreeds> = MainRepository.getAllBreeds()

    val listofbreed: LiveData<ListofBreedLinks> = Transformations
        .switchMap(breed){
            MainRepository.getBreedsList(it)

        }

    fun setBreed(dog : String){
        val update = dog
        if(breed.value == update){
            Log.e("same", "same")
            return
        }
        breed.value = update
        Log.e("Dome", "Done")
    }

    fun cancelJobs(){
        MainRepository.cancelJobs()
    }

    /*
    fun getPost(){
        viewModelScope.launch{
            val response = repository.getPost()
            myRandom.value = response
        }
    }

    fun getBreed(){
        viewModelScope.launch {
            val response = repository.getBreed()
            myBreed.value = response
        }
    }

    fun getAllBreeds(){
        viewModelScope.launch {
            val response = repository.getAllBreeds()
            myBreedsAll.value = response

        }
    }

    fun getListofBreed(dog : String) = GlobalScope.async{
        viewModelScope.launch {
            val response = repository.getListofBreed(dog)
            myWantedBreedList.value = response

        }
    }


     */

}