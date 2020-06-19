package com.example.nikecodingchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.nikecodingchallenge.R
import com.example.nikecodingchallenge.viewmodel.AppViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: AppViewModel
    val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        observeBackEnd()
        viewModel.getSearchResult("wat")
    }

    private fun observeBackEnd() {

        viewModel.searchResultSuccess.observe(this, Observer {

            val test = it.list!!.get(0).author
            Log.d(TAG,"Test: $test")
        })
    }
}