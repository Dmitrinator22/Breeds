package com.example.cutedogbreeds

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cutedogbreeds.repository.Repository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

     private var listD = mutableListOf<String>()
     private var listL = mutableListOf<String>()
     //SMS kommt jedesmal + das alte vor und erste reihe ist immer vom erst gedrückten!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*
        setContentView(R.layout.activity_main)

        var listView = findViewById<ListView>(R.id.listview)
        var list = mutableListOf<Breed>()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        val viewModel: MainViewModel
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        list.add(Breed("Labrador"))
        list.add(Breed("Doberman"))
        list.add(Breed("Pitbull"))
        list.add(Breed("Akita"))
        list.add(Breed("Dingo"))
        list.add(Breed("Poodle"))
        list.add(Breed("Rottweiler"))
        list.add(Breed("Springer"))
        list.add(Breed("Waterdog"))


        listView.adapter = BreedAdapter(this, R.layout.raw, list)

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->


        }
        */

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        val viewModel: MainViewModel
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        startUp(viewModel)

        /*
        viewModel.getAllBreeds()

        viewModel.myBreedsAll.observe(this, Observer { response ->

            if(response.isSuccessful) {
                val time = measureTimeMillis {

                Log.e("SMS r to String", response.body()?.message.toString())


                    for (element in response.body()?.message!!) {
                        listD.add(element)
                        list.add(Breed(element.first().toUpperCase() + element.drop(1)))
                     }

                }
                Log.e("Time", "Requests $time ms")


            }else{
                Log.e("SMS fail", response.errorBody().toString())
            }

        })


         */

        /*
        var button: Button = findViewById(R.id.show)

        button.setOnClickListener(View.OnClickListener {

            listView.adapter = BreedAdapter(this, R.layout.raw, list)

            Log.e("ListL->", listL.toString())
            Log.e("ListD->", listD.toString())

            listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
                listL.clear()
                Log.e("Breed ID", " "+ id)


                viewModel.getListofBreed(listD[position].toLowerCase())

                viewModel.myWantedBreedList.observe(this, Observer { response ->

                    GlobalScope.launch (Dispatchers.Main) {
                        if(response.isSuccessful) {

                            val time = measureTimeMillis {

                                //Log.e("SMS Successful", response.body()?.message.toString())
                                val job = launch {

                                    listL.clear()
                                    for(element in response.body()?.message!!){
                                        listL.add(element)

                                    }
                                    //Log.e("ListL->", listL.toString())

                                }
                                job.join()

                            }
                            Log.e("Time get All", "took $time ms")
                            Log.e("ListL->", listL.toString())

                            val jobNewLayout = launch {

                                newLayout()
                                Log.e("New Layout", "done")
                            }

                            jobNewLayout.join()

                        }else{
                            Log.e("SMS fail", response.errorBody().toString())
                        }


                    }






                                //Log.e("ListL", listL.toString())


                })

                //Log.e("ListL->", listL.toString())
            }



        })

         */


    }

    fun startUp(viewModel: MainViewModel){
        setContentView(R.layout.activity_main)

        var listView = findViewById<ListView>(R.id.listview)
        var list = mutableListOf<Breed>()

        getAllBreeds(viewModel, list)


        var button: Button = findViewById(R.id.show)

        button.setOnClickListener(View.OnClickListener {

            listView.adapter = BreedAdapter(this, R.layout.raw, list)

            Log.e("ListL->", listL.toString())
            Log.e("ListD->", listD.toString())

            listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
                listL.clear()
                Log.e("Breed ID", " " + id)
                getClickedBreed(viewModel, listD[position].toLowerCase())

            }

        })

    }

    fun getClickedBreed(viewModel: MainViewModel, breed : String){

        viewModel.getListofBreed(breed)
        viewModel.myWantedBreedList.observe(this, Observer { response ->
            if(response.isSuccessful) {

                GlobalScope.launch(Dispatchers.IO) {
                    val time = measureTimeMillis {

                        val job = launch {

                            listL.clear()
                            for(element in response.body()?.message!!){
                                listL.add(element)

                            }
                            //Log.e("ListL->", listL.toString())

                        }
                        job.join()

                    }

                    Log.e("Time", "took $time ms")
                    Log.e("ListL", listL.toString())

                }
                newLayout(listL, viewModel)
            }else{
                Log.e("Error Response", response.errorBody().toString())
            }

        })


    }

    fun getAllBreeds(viewModel: MainViewModel, list: MutableList<Breed>) {

        viewModel.getAllBreeds()

        viewModel.myBreedsAll.observe(this, Observer { response ->

            if(response.isSuccessful) {
                GlobalScope.launch(Dispatchers.IO) {
                    val time = measureTimeMillis {

                        //Log.e("SMS r to String", response.body()?.message.toString())
                        val job = launch {
                            for (element in response.body()?.message!!) {
                                listD.add(element)
                                list.add(Breed(element.first().toUpperCase() + element.drop(1)))
                            }

                        }
                        job.join()

                    }
                    Log.e("Time", "Requests $time ms")
                }


            }else{
                Log.e("SMS fail", response.errorBody().toString())
            }

        })

    }


    fun newLayout(listL: MutableList<String>, viewModel: MainViewModel) {

        setContentView(R.layout.info)

        val imgview : ImageView = findViewById(R.id.imgV)

        Picasso.get().load(listL[0]).into(imgview)

        val exit: Button = findViewById(R.id.backbutton)

        val next: Button = findViewById(R.id.nextBreed)

        exit.setOnClickListener(View.OnClickListener {

            startUp(viewModel)

        })

        next.setOnClickListener(View.OnClickListener {
            val nr = Random.nextInt(listL.size)
            Picasso.get().load(listL[nr]).into(imgview)
        })

    }

    fun showThem(view: View) {





    }

/*
    fun startPage(){
        setContentView(R.layout.activity_main)



        var listView = findViewById<ListView>(R.id.listview)
        var list = mutableListOf<Dog>()

        list.add(Dog("Labrador", R.drawable.labrador))
        list.add(Dog("Doberman", R.drawable.dobermann))
        list.add(Dog("Pitbull", R.drawable.pitbull))
        list.add(Dog("Akita", R.drawable.labrador))
        list.add(Dog("Dingo", R.drawable.dobermann))
        list.add(Dog("Poodle", R.drawable.pitbull))
        list.add(Dog("Rottweiler", R.drawable.labrador))
        list.add(Dog("Springer", R.drawable.dobermann))
        list.add(Dog("Waterdog", R.drawable.pitbull))


        listView.adapter = Adapter(this, R.layout.raw, list)

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->





                //val breed = getName(position, list)
                //Log.e("position", "Position: "+position + ", Breed: "+breed);
                //newInfo(breed);


                //listByBreed(list.get(position).breed.toLowerCase())






/*
                for (index in 0..response.message.size-1){
                    println("Link "+index+" :..."+response.message[index])
                    listD.add(index,response.message[index])


                }
                println("****************")
*/
                //var breed = Breed(dog, response.message[0])
                //listBreed.add(breed)
                // to sow all separat
                // listAll(response.message)





            setContentView(R.layout.info)


            val exit: Button = findViewById(R.id.backbutton)
            exit.setOnClickListener(View.OnClickListener {
                Log.e("SMS from Heaven", listD.toString())
                //startPage()


                //getListofBreed()
                //viewModel.myBreedList.removeObservers(this)
                //viewModel.closeIt()
            })

        }



    }

*/







}





