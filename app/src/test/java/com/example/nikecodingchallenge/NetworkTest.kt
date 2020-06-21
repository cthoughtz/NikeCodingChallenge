package com.example.nikecodingchallenge

import com.example.nikecodingchallenge.di.networkModule
import com.example.nikecodingchallenge.di.vmModule
import com.example.nikecodingchallenge.viewmodel.AppViewModel
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.Retrofit

class NetworkTest : KoinTest {

    val vm: AppViewModel by inject()
    val retrofit: Retrofit by inject()
    val okHttpClient: OkHttpClient by inject()
    val baseUrl = "https://mashape-community-urban-dictionary.p.rapidapi.com"

    @Before
    fun setup(){

        startKoin {
            modules(listOf(networkModule, vmModule))
        }
    }


    @Test
    fun `Test Retrofit Created`(){

        assertNotNull(retrofit)
    }

    @Test
    fun `Test ViewModel Created`(){

        assertNotNull(vm)
    }

    @Test
    fun `Test OkHttp`(){

        assertNotNull(okHttpClient)
        assertTrue(okHttpClient.connectTimeoutMillis == 30000)
        assertTrue(okHttpClient.readTimeoutMillis == 30000)
        assertTrue(okHttpClient.interceptors.size == 1)
    }

    @After
    fun tearDown(){
        stopKoin()
    }

}