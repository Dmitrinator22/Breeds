package com.example.cutedogbreeds

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class BreedAdapter(var mCtx:Context, var resource:Int, var items: MutableList<String>):ArrayAdapter<String>(mCtx, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(resource, null)
        val textView:TextView = view.findViewById(R.id.textview)
        val dog: String = items[position]
        textView.text = dog

        return view
    }
/*
    fun getBreed(pos:Int): String {

        val breed:String = items[pos]

        Log.e("AdapterWork","Clicked Breed: "+breed+" ")
        return breed
    }

 */
}