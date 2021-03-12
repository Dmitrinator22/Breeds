package com.example.cutedogbreeds


import android.content.Intent
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
import com.example.cutedogbreeds.net.ConnectionLiveData
import com.example.cutedogbreeds.viewmodels.BreedModelFactory
import com.example.cutedogbreeds.viewmodels.BreedViewModel

import com.example.cutedogbreeds.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

 class MainActivity : AppCompatActivity() {

    private var listBreeds = mutableListOf<String>()
    private var job: CompletableJob? = null
    private lateinit var viewModel: MainViewModel
    private var network: Boolean = false
     lateinit var connectionLiveData: ConnectionLiveData
     private var info: Boolean = false
    private val breedViewModel: BreedViewModel by viewModels {
        BreedModelFactory((application as BreedApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("Action", "OnCreate")
        setContentView(R.layout.activity_main)

        job=Job()
        connectionLiveData = ConnectionLiveData(this)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    fun breedsFromAPI(listView: ListView){
        viewModel.listofall.observe(this, Observer { result->
            Log.e("All Breeds", result.message.toString())

                CoroutineScope(Dispatchers.IO).launch {
                    for (element in result.message){
                        listBreeds.add(element[0].toUpperCase()+element.drop(1))

                    }
                    withContext(Dispatchers.Main){
                        showBreeds(listView)
                    }
                }

        })
    }

    fun breedsFromDB(listView: ListView){

        breedViewModel.allBreeds.observe(this, Observer {result->
            CoroutineScope(Dispatchers.IO).launch {
                for(element in result){
                    listBreeds.add(element.breed)
                }
                withContext(Dispatchers.Main){
                    showBreeds(listView)
                }

            }
        })


    }


    fun showBreeds(listView: ListView){
        listView.adapter = BreedAdapter(this, R.layout.raw, listBreeds)
        Log.e("showBreeds()", listBreeds.toString())
    }

    override fun onPause() {
        super.onPause()
        Log.e("Action", "OnPause")

    }

    override fun onStop() {
        super.onStop()
        Log.e("Action", "OnStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("Action", "OnRestart")

    }

    override fun onResume() {
        super.onResume()
        Log.e("Action", "OnResume")

        val intent:Intent =Intent(this, BreedActivity::class.java)
        val listView = findViewById<ListView>(R.id.listview)

        connectionLiveData.observe(this, Observer {
            network = it
            Log.e("NET set $it", "= $network")

            info = true

            GlobalScope.launch {
                listBreeds.clear()
                if (network){
                    listBreeds.clear()
                    Log.e("NET t", "= $network")
                    val job = CoroutineScope(Dispatchers.Main).launch {
                        breedsFromAPI(listView)
                    }
                    job.join()
                    //Toast.makeText(this, "List from API", Toast.LENGTH_SHORT).show()

                }else{
                    Log.e("NET f", "= $network")
                    listBreeds.clear()
                    val job = CoroutineScope(Dispatchers.Main).launch {
                        breedsFromDB(listView)
                    }
                    job.join()
                    //Toast.makeText(this, "List from DB", Toast.LENGTH_SHORT).show()
                }
            }
        })

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
             val breed:String = listBreeds[position]
             Log.e("Pos", "Clicked $position and $breed ")
            intent.putExtra("breed", breed)
             startActivity(intent)

        }

        updateView.setOnClickListener {
            listBreeds.clear()
            breedsFromDB(listView)
            Toast.makeText(this, "List from DB", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.e("Action", "OnStart")

    }

}






