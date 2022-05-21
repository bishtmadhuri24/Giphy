package com.example.giphy.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphy.DataObject

import com.example.giphy.R

class RandomDataAdapter(val context: Context,val datalist:List<DataObject>):
    RecyclerView.Adapter<RandomDataAdapter.DataHolder>() {
    class DataHolder(itemview:View):RecyclerView.ViewHolder(itemview)
    {
        val image:ImageView=itemview.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
       val view=LayoutInflater.from(context).inflate(R.layout.data_layout,parent,false)
        return DataHolder(view)
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
      val data=datalist[position]
        Glide.with(context).load(data.images.ogImage.url).into(holder.image)
    }

    override fun getItemCount()=datalist.size
}