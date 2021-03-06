package com.example.cutedogbreeds

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private var listBreeds = mutableListOf<String>()
    private var job: CompletableJob? = null
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent:Intent =Intent(this, BreedActivity::class.java)
        val listView = findViewById<ListView>(R.id.listview)

        job= Job()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.listofall.observe(this, Observer { result->
            Log.e("All Breeds", result.message.toString())
                job?.let {
                    CoroutineScope(Dispatchers.IO+it).launch {
                        for (element in result.message){
                            listBreeds.add(element[0].toUpperCase()+element.drop(1))
                        }
                        withContext(Dispatchers.Main){
                            showBreeds(listView)
                        }
                    }
                }
        })

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->

            val breed:String = listBreeds[position].toLowerCase()
            Log.e("Pos", "Clicked $position and $breed ")
            intent.putExtra("breed", breed)
            startActivity(intent)

        }

    }

    fun showBreeds(listView: ListView){
        listView.adapter = BreedAdapter(this, R.layout.raw, listBreeds)

    }


}





