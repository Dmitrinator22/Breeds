package com.example.cutedogbreeds

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class BreedAdapter(var mCtx:Context, var resource:Int, var items:List<Breed>):ArrayAdapter<Breed>(mCtx, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)

        val view:View = layoutInflater.inflate(resource, null)

        val textView:TextView = view.findViewById(R.id.textview)

        val dog:Breed = items[position]

        textView.text = dog.breed

        return view
    }

    fun getBreed(pos:Int): String {

        val breed:String = items[pos].breed

        Log.e("AdapterWork","Clicked Breed: "+breed+" ")
        return "This is the breed u have clicked on: "+breed
    }
}