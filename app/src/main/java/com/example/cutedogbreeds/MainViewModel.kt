package com.example.cutedogbreeds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cutedogbreeds.model.ListBreed
import com.example.cutedogbreeds.model.Post
import com.example.cutedogbreeds.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel(){

    val myResponse: MutableLiveData<Post> = MutableLiveData()
    val myListResponse: MutableLiveData<ListBreed> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch{
            val response = repository.getPost()
            myResponse.value = response
        }
    }

    fun getListBreed(){
        viewModelScope.launch {
            val response = repository.getListBreed()
            myListResponse.value = response
        }
    }

}