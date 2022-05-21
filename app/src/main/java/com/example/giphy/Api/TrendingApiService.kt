package com.example.giphy.Api

import com.example.giphy.DataResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
//https://api.giphy.com/v1/gifs/trending?api_key=vtlLHabCgeggROgtrD2qUIDjSaUp8c6k&limit=25&rating=g
interface TrendingApiService {
    @GET("gifs/trending?api_key=vtlLHabCgeggROgtrD2qUIDjSaUp8c6k")
    fun getRandom():Call<DataResult>
}