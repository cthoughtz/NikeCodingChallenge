package com.example.nikecodingchallenge.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object PostApi{

    private val baseUrl ="https://mashape-community-urban-dictionary.p.rapidapi.com"
    private val loggingInterceptor = HttpLoggingInterceptor()
        .also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30,TimeUnit.SECONDS)
        .build()

    val retrotfit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    val postApi: ApiService by lazy {

        retrotfit.create(ApiService::class.java)
    }
}