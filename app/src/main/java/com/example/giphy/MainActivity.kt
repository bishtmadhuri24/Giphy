package com.example.giphy


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphy.Adapter.DataAdapter
import com.example.giphy.Adapter.RandomDataAdapter
import com.example.giphy.Api.TrendingApiService
import com.example.giphy.ViewModel.GiphViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    val random= mutableListOf<DataObject>()
    val baseurl="https://api.giphy.com/v1/"
    lateinit var searchGiphadapter:DataAdapter
    lateinit var randomGiphadapter:RandomDataAdapter
    lateinit var search:EditText
    lateinit var click:Button
    lateinit var viewModel:GiphViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search=findViewById(R.id.search)
       click=findViewById(R.id.click)
        val recyclerView: RecyclerView =findViewById(R.id.recycler)

        recyclerView.layoutManager= GridLayoutManager(this,2)
        recyclerView.setHasFixedSize(true)
        searchGiphadapter=DataAdapter(this)
        randomGiphadapter=RandomDataAdapter(this,random)
        recyclerView.adapter=randomGiphadapter
        randomGiphadapter=RandomDataAdapter(this@MainActivity,random)

        createData()
        createAnotherData()



    }

    private fun createAnotherData() {
        val retro=Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrodata=retro.create(TrendingApiService::class.java)
            .getRandom()
        retrodata.enqueue(object : Callback<DataResult?> {
            override fun onResponse(call: Call<DataResult?>, response: Response<DataResult?>) {
                val body=response.body()
                if (body==null){
                    Log.d("main","error")
                }
                random.addAll(body!!.data)
            }

            override fun onFailure(call: Call<DataResult?>, t: Throwable) {
                Log.d("main","error")
            }
        })
    }

   private fun createData() {
        click.setOnClickListener {
            viewModel.makeApiCall(search.text.toString())

        }

       viewModel=ViewModelProvider(this).get(GiphViewModel::class.java)
        viewModel.getGiphListObserver().observe(this, Observer<DataResult> {
            if (it!=null){
                searchGiphadapter.setGiphData(random as ArrayList<DataObject>)

                searchGiphadapter.setGiphData(it.data as ArrayList<DataObject>)
                searchGiphadapter.notifyDataSetChanged()
            }
            else{
                Toast.makeText(this,"error",Toast.LENGTH_LONG).show()
            }
        })
    }

}