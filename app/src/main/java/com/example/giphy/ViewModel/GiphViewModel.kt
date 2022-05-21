package com.example.giphy.ViewModel

import android.security.identity.ResultData
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


import com.example.giphy.Api.GiphInstance
import com.example.giphy.Api.SearchApiService
import com.example.giphy.DataObject
import com.example.giphy.DataResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GiphViewModel():ViewModel() {
    lateinit var giphListData: MutableLiveData<DataResult>
    //lateinit var trendingGifData:MutableLiveData<DataResult>
init {
    giphListData= MutableLiveData()
}
    fun getGiphListObserver():MutableLiveData<DataResult>{
        return giphListData
    }

    fun makeApiCall(input:String){
      val retroinstance=GiphInstance.giphGetInstance().create(SearchApiService::class.java)
       val call=retroinstance.getData(input)
        call.enqueue(object : Callback<DataResult?> {
            override fun onResponse(call: Call<DataResult?>, response: Response<DataResult?>) {
               if (response.isSuccessful){

                   giphListData.postValue(response.body())

               }
                else{ giphListData.postValue(null)}
            }

            override fun onFailure(call: Call<DataResult?>, t: Throwable) {
                giphListData.postValue(null)
            }
        })
    }
}

