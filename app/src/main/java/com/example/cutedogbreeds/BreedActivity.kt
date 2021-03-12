package com.example.cutedogbreeds

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
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
import kotlinx.android.synthetic.main.info.*
import kotlinx.coroutines.*

class BreedActivity :AppCompatActivity() {

    private lateinit var slider: SlidrInterface
    lateinit var viewModel: MainViewModel
    private var job: CompletableJob? = null
    private val breedViewModel: BreedViewModel by viewModels {
        BreedModelFactory((application as BreedApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)
        val breed: String = intent.getStringExtra("breed")
        Log.e("Breed", breed)
        val imageView : ImageView = findViewById(R.id.imgV)
        //val list: Array<String> = intent.getStringArrayExtra("list")

        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (isConnected){
            Toast.makeText(this, "Say hi", Toast.LENGTH_SHORT).show()
            viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
            viewModel.setBreed(breed.toLowerCase())
            viewModel.listofbreed.observe(this, Observer { result->
                CoroutineScope(Dispatchers.IO).launch {
                    Log.e("Breed Links", result.message.toString())
                    withContext(Dispatchers.Main){
                        Picasso.get().load(result.message?.get(0)).into(imageView)
                        //breedViewModel.insert(Breed(0,breed))
                    }
                }
            })
        }else{
                breedViewModel.getOneBreed(breed)
                breedViewModel.myBreed.observe(this, Observer {result->
                    Picasso.get().load(result.links).into(imageView)
                    Toast.makeText(this, "Say hi", Toast.LENGTH_SHORT).show()

                })
        }

        breedViewModel.allBreeds.observe(this, Observer {result->
            result.let {
                Log.e("DBAllBreeds", result.toString())
            }
        })

        slider = Slidr.attach(this)
        slider.unlock()

        loadImg.setOnClickListener {
            if (isConnected){
                viewModel.setBreed(breed.toLowerCase())
                viewModel.listofbreed.observe(this, Observer { result->
                    CoroutineScope(Dispatchers.IO).launch {
                        breedViewModel.insert(Breed(0,breed, result.message?.get(0).toString()))
                    }
                })
                Toast.makeText(this, "You now can see this good boy offline too", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "You are Offline", Toast.LENGTH_SHORT).show()
            }
        }

        deleteImg.setOnClickListener {
            breedViewModel.deleteBreed(breed)
            Toast.makeText(this, "You will not see this cute boy offline anymore", Toast.LENGTH_LONG).show()

        }

    }

}