package com.example.nikecodingchallenge.di

import com.example.nikecodingchallenge.network.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    //Provide GSON
    single<Gson>{
        GsonBuilder().create()
    }

    //HttpLoggingInterceptor
    single {
        HttpLoggingInterceptor()
            .also {
                it.level = HttpLoggingInterceptor.Level.BODY
            }
    }

    single {

        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .readTimeout(30,TimeUnit.SECONDS)
            .connectTimeout(30,TimeUnit.SECONDS)
            .build()
    }

    single<Retrofit>{

        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
            .build()
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }
}