package com.example.cutedogbreeds.viewmodels

import androidx.lifecycle.*
import com.example.cutedogbreeds.db.Breed
import com.example.cutedogbreeds.repository.DbRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class BreedViewModel (private val repository: DbRepository) : ViewModel() {

    val allBreeds: LiveData<List<Breed>> = repository.allBreeds.asLiveData()


    fun insert(breed: Breed) = viewModelScope.launch {
        repository.insert(breed)
    }

}

class BreedModelFactory(private val repository: DbRepository):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BreedViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return BreedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
