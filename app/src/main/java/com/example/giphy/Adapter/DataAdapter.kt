package com.example.giphy.Adapter

import android.app.Activity
import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphy.DataObject
import com.example.giphy.DataResult
import com.example.giphy.OgImage
import com.example.giphy.R

class DataAdapter(val activty:Activity) :
    RecyclerView.Adapter<DataAdapter.DataHolder>() {
   var giflist=ArrayList<DataObject>()
    class DataHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val images=itemview.findViewById<ImageView>(R.id.image)

    }

    fun setGiphData(data: ArrayList<DataObject>){
        this.giflist= data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {

        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.data_layout,parent,false)
        return DataHolder(view)
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
       val data=giflist[position]
        Glide.with(activty).asGif().load(data.images.ogImage.url).into(holder.images)
    }

    override fun getItemCount()=giflist.size

}