package com.example.giphy.Api


import com.example.giphy.DataResult
import retrofit2.http.GET
import retrofit2.http.Query
interface SearchApiService {
    @GET("gifs/search?api_key=vtlLHabCgeggROgtrD2qUIDjSaUp8c6k")
    fun getData(@Query("q")query:String):retrofit2.Call<DataResult>
}