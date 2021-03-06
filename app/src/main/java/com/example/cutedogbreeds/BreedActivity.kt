package com.example.cutedogbreeds

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrInterface
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class BreedActivity :AppCompatActivity() {

    private lateinit var slider: SlidrInterface
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)
        val breed: String = intent.getStringExtra("breed")
        var imageView : ImageView = findViewById(R.id.imgV)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.setBreed(breed)
        viewModel.listofbreed.observe(this, Observer { result->

                CoroutineScope(Dispatchers.IO).launch {
                    Log.e("Breed Links", result.message.toString())
                    withContext(Dispatchers.Main){
                        Picasso.get().load(result.message?.get(0)).into(imageView)
                    }
                }

        })
        slider = Slidr.attach(this)
        slider.unlock()
    }

}