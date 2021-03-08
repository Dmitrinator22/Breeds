package com.example.cutedogbreeds

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cutedogbreeds.db.Breed
import com.example.cutedogbreeds.viewmodels.BreedModelFactory
import com.example.cutedogbreeds.viewmodels.BreedViewModel
import com.example.cutedogbreeds.viewmodels.MainViewModel
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrInterface
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class BreedActivity :AppCompatActivity() {

    private lateinit var slider: SlidrInterface
    lateinit var viewModel: MainViewModel

    private val breedViewModel: BreedViewModel by viewModels {
        BreedModelFactory((application as BreedApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)
        val breed: String = intent.getStringExtra("breed")
        var imageView : ImageView = findViewById(R.id.imgV)




        breedViewModel.allBreeds.observe(this, Observer {result->

            result.let {
                Log.e("Tag", result.toString())
            }

        })


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.setBreed(breed)
        viewModel.listofbreed.observe(this, Observer { result->

                CoroutineScope(Dispatchers.IO).launch {
                    Log.e("Breed Links", result.message.toString())
                    withContext(Dispatchers.Main){
                        Picasso.get().load(result.message?.get(0)).into(imageView)
                        breedViewModel.insert(Breed(0,breed, result.message?.get(0).toString()))
                    }
                }

        })
        slider = Slidr.attach(this)
        slider.unlock()
    }

}