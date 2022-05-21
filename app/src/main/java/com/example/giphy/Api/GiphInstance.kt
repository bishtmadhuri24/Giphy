package com.example.giphy.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GiphInstance {
    companion object{
        val baseurl="https://api.giphy.com/v1/"
        fun giphGetInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}