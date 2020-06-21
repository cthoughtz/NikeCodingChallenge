package com.example.nikecodingchallenge

import com.example.nikecodingchallenge.di.networkModule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class ModuleTest: KoinTest {

    @Test
    fun `Test Koin Modules`(){

        startKoin {
            modules(listOf(networkModule))
        }.checkModules()

        stopKoin()
    }
}