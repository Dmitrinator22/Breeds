package com.example.cutedogbreeds

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class Adapter(var mCtx:Context, var resource:Int, var items:List<Dog>):ArrayAdapter<Dog>(mCtx, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)

        val view:View = layoutInflater.inflate(resource, null)

        val imageView:ImageView = view.findViewById(R.id.imageview)
        val textView:TextView = view.findViewById(R.id.textview)


        val dog:Dog = items[position]
        imageView.setImageDrawable(mCtx.resources.getDrawable(dog.img))
        textView.text = dog.breed
        Log.e("Name Dog", "Breed: "+dog.breed)

        return view
    }

    fun getBreed(pos:Int): String {

        val breed:String = items[pos].breed

        Log.e("AdapterWork","Clicked Breed: "+breed+" ")
        return "This is the breed u have clicked on: "+breed
    }
}