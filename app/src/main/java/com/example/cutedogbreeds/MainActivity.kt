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
import com.example.cutedogbreeds.model.AllBreeds
import com.example.cutedogbreeds.repository.Repository

 class MainActivity : AppCompatActivity() {

     private var listD = mutableListOf<String>()

     //SMS kommt jedesmal + das alte vor und erste reihe ist immer vom erst gedrückten!!
     
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var listView = findViewById<ListView>(R.id.listview)
        var list = mutableListOf<Breed>()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        val viewModel: MainViewModel
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        /*
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


        viewModel.getAllBreeds()

        viewModel.myBreedsAll.observe(this, Observer { response ->

            if(response.isSuccessful) {

                Log.e("SMS r to String", response.body()?.message.toString())

                for (element in response.body()?.message!!) {
                    listD.add(element)
                    list.add(Breed(element.first().toUpperCase() + element.drop(1)))
                }

            }else{
                Log.e("SMS fail", response.errorBody().toString())
            }

        })

        var button: Button = findViewById(R.id.show)

        button.setOnClickListener(View.OnClickListener {

            listView.adapter = BreedAdapter(this, R.layout.raw, list)

            Log.e("ListD->", listD.toString())

            listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->

                Log.e("Breed ID", " "+ id)

                viewModel.getListofBreed(listD[position].toLowerCase())

                viewModel.myWantedBreedList.observe(this, Observer { response ->

                    if(response.isSuccessful) {

                        Log.e("SMS Successful", response.body()?.message.toString())

                    }else{
                        Log.e("SMS fail", response.errorBody().toString())
                    }

                })


            }



        })




    }

     fun listAll(list: List<String>){

         for(element in list){
             Log.e("Element", element)

             //listByBreed(element)

         }

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





