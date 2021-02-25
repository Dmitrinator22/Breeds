package com.example.cutedogbreeds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

/*
        var listView = findViewById<ListView>(R.id.listview)
        var list = mutableListOf<Dog>()

        list.add(Dog("Labrador", R.drawable.labrador))
        list.add(Dog("Dobermann", R.drawable.dobermann))
        list.add(Dog("Pitbull", R.drawable.pitbull))

        listView.adapter = Adapter(this, R.layout.raw, list)

        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val pos:Int = position;

            Log.e("position", "Position: "+pos);
            newInfo(pos);



            val exit:Button = findViewById(R.id.backbutton)
            exit.setOnClickListener(View.OnClickListener {

              setContentView(R.layout.activity_main);

            })

        }
*/


        startPage();


    }

    fun newInfo(pos: Int) {

        setContentView(R.layout.info)

        val text:TextView = findViewById(R.id.info)

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
            val pos:Int = position;



            Log.e("position", "Position: "+pos);
            newInfo(pos);

            val exit:Button = findViewById(R.id.backbutton)
            exit.setOnClickListener(View.OnClickListener {

                startPage()

            })

        }

    }

}