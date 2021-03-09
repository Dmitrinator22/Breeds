package com.example.cutedogbreeds

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cutedogbreeds.db.Breed
import com.example.cutedogbreeds.viewmodels.BreedModelFactory
import com.example.cutedogbreeds.viewmodels.BreedViewModel

import com.example.cutedogbreeds.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private var listBreeds = mutableListOf<String>()
    private var job: CompletableJob? = null
    private lateinit var viewModel: MainViewModel

    private val breedViewModel: BreedViewModel by viewModels {
        BreedModelFactory((application as BreedApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent:Intent =Intent(this, BreedActivity::class.java)
        val listView = findViewById<ListView>(R.id.listview)
        //showBreeds(listView)
        job= Job()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if(isConnected){
            viewModel.listofall.observe(this, Observer { result->
                Log.e("All Breeds", result.message.toString())
                job?.let {
                    CoroutineScope(Dispatchers.IO+it).launch {
                        for (element in result.message){
                            listBreeds.add(element[0].toUpperCase()+element.drop(1))
                            //breedViewModel.insert(Breed(0, element.toString()))
                        }
                        withContext(Dispatchers.Main){
                            showBreeds(listView)
                        }
                    }
                }
            })
            showBreeds(listView)

        }else{

            listBreeds.clear()

            breedViewModel.allBreeds.observe(this, Observer {result->

                for(element in result){
                    //listBreeds.add(element.breed+ " "+ element.id)
                    listBreeds.add(element.breed)

                }

            })

            showBreeds(listView)

        }

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->

            val breed:String = listBreeds[position]
            Log.e("Pos", "Clicked $position and $breed ")
            intent.putExtra("breed", breed)
            startActivity(intent)

        }

        /*
        load.setOnClickListener {

            val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

            if (isConnected) {
                breedViewModel.deleteBreeds()
                for (element in listBreeds){
                    breedViewModel.insert(Breed(0, element.toString()))
                }
                Toast.makeText(this, "Downloaded", Toast.LENGTH_SHORT).show()
            }else{
                /*
                repeat(5) {
                    breedViewModel.insert(Breed(0, "Dmitri"))
                }
                */
                Toast.makeText(this, "You are OFFLINE", Toast.LENGTH_SHORT).show()
            }



            /*


             */

        }

        update.setOnClickListener {
            Toast.makeText(this, "Update Clicked", Toast.LENGTH_SHORT).show()

            val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

            if(isConnected){
                viewModel.listofall.observe(this, Observer { result->
                    Log.e("All Breeds", result.message.toString())
                    job?.let {
                        CoroutineScope(Dispatchers.IO+it).launch {
                            for (element in result.message){
                                listBreeds.add(element[0].toUpperCase()+element.drop(1))
                                //breedViewModel.insert(Breed(0, element.toString()))
                            }
                            withContext(Dispatchers.Main){
                                showBreeds(listView)
                            }
                        }
                    }
                })
            }else{

                listBreeds.clear()
                breedViewModel.allBreeds.observe(this, Observer {result->

                    for(element in result){
                        //listBreeds.add(element.breed+ " "+ element.id)
                        listBreeds.add(element.breed)

                    }

                })


            }

            showBreeds(listView)



        }

        delete.setOnClickListener {
            Toast.makeText(this, "Delete Clicked", Toast.LENGTH_SHORT).show()
            listBreeds.clear()
            breedViewModel.deleteBreeds()
            showBreeds(listView)
        }

         */

    }

    fun showBreeds(listView: ListView){

        listView.adapter = BreedAdapter(this, R.layout.raw, listBreeds)

    }


}





