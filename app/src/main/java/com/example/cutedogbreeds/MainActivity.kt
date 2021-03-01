package com.example.cutedogbreeds

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cutedogbreeds.repository.Repository

 class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initRepository()
        getListofBreed()
        getAllBreeds()
        listByBreed("husky")

        startPage()

        val thread = SimpleThread()
        thread.start()






    }

     fun listByBreed(dog : String){
         viewModel.getListofBreed(dog)

         viewModel.myBreedList.observe(this, Observer { response->
             Log.e("SMS breed of type: "+ dog, response.message.toString())

         })

     }



     fun listAll(list: List<String>){

         for(element in list){
             Log.e("Element", element)

         }

     }


     private fun getAllBreeds(){

        viewModel.getAllBreeds()

        viewModel.myBreedsResponse.observe(this, Observer { response->
            Log.e("Sms", response.message.toString())

            listAll(response.message)

            //val breeds: List<String>

            //breeds = response.message.toList()

            //Log.e("SMS", breeds.toString())

            //Log.e("Response", response.status)
            // Picasso.get().load(response.message[2]).into(imageview)
        })

    }


     fun initRepository(){
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

    }

     fun getListofBreed() {

        viewModel.getListBreed()

        viewModel.myListResponse.observe(this, Observer { response->
            Log.e("Response", response.message[1])
            //Log.e("Response", response.status)
            // Picasso.get().load(response.message[2]).into(imageview)
        })
    }


    fun newInfo(breed: String) {
        setContentView(R.layout.info)
        val text: TextView = findViewById(R.id.info)
        val textToWrite: String
    }

    fun startPage(){
        setContentView(R.layout.activity_main)

        var listView = findViewById<ListView>(R.id.listview)
        var list = mutableListOf<Dog>()

        list.add(Dog("Labrador", R.drawable.labrador))
        list.add(Dog("Dobermann", R.drawable.dobermann))
        list.add(Dog("Pitbull", R.drawable.pitbull))

        listView.adapter = Adapter(this, R.layout.raw, list)

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->

            val breed = getName(position, list)
            listByBreed(breed.toLowerCase())
            Log.e("position", "Position: "+position + ", Breed: "+breed);
            newInfo(breed);

            val exit: Button = findViewById(R.id.backbutton)
            exit.setOnClickListener(View.OnClickListener {

                startPage()
                getListofBreed()

            })

        }



    }

    fun getName(id:Int, list: List<Dog>):String{

        Log.e("GetName()",list.get(id).breed)

        return list.get(id).breed
    }

    class SimpleThread:Thread(){
        public override fun run() {

            Log.e("Hello", "Hi")

        }



    }



}

