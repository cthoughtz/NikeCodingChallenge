package com.example.nikecodingchallenge.network

import com.example.nikecodingchallenge.model.UrbanDictionaryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers(
        "x-rapidapi-host: mashape-community-urban-dictionary.p.rapidapi.com",
        "x-rapidapi-key: 256b88dec8msh0a717426c5de7fap117097jsndac4f78569e1"
    )
    @GET("/define")
    suspend fun searchTerm(@Query("term") searchTerm:String): Response<UrbanDictionaryResponse>
}